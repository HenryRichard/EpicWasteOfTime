package henryrichard.epicwasteoftime.client.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import henryrichard.epicwasteoftime.EwotMain;
import net.minecraft.client.renderer.TransformationMatrix;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.VanillaResourceType;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MaskedToolModel implements IModelGeometry<MaskedToolModel> {

    public static final ModelResourceLocation LOCATION = new ModelResourceLocation(new ResourceLocation(EwotMain.MODID, "masked_tool"), "inventory");

    public MaskedToolModel() {}

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        TextureAtlasSprite handleSprite = getSpriteIfExistent(owner, spriteGetter, "handle");
        TextureAtlasSprite handleMaskSprite = getSpriteIfExistent(owner, spriteGetter, "handle_mask");

        TextureAtlasSprite headSprite = getSpriteIfExistent(owner, spriteGetter, "head");
        TextureAtlasSprite headMaskSprite = getSpriteIfExistent(owner, spriteGetter, "head_mask");

        TextureAtlasSprite particle = getSpriteIfExistent(owner, spriteGetter, "particle");

        IModelTransform transformsFromModel = owner.getCombinedTransform();
        ImmutableMap<ItemCameraTransforms.TransformType, TransformationMatrix> transformMap = transformsFromModel != null ?
                PerspectiveMapWrapper.getTransforms(new ModelTransformComposition(transformsFromModel, modelTransform)) :
                PerspectiveMapWrapper.getTransforms(modelTransform);

        TransformationMatrix transform = modelTransform.getRotation();

        ImmutableList.Builder<BakedQuad> quadBuilder = ImmutableList.builder();
        if(handleSprite != null) {
            if(handleMaskSprite != null) {
                quadBuilder.addAll(ItemTextureQuadConverter.convertTexture(transform, handleMaskSprite, handleSprite, 7.5f/16f, Direction.NORTH, 0xFFFFFFFF, 1));
                quadBuilder.addAll(ItemTextureQuadConverter.convertTexture(transform, handleMaskSprite, handleSprite, 8.5f/16f, Direction.SOUTH, 0xFFFFFFFF, 1));
            } else {
                quadBuilder.addAll(ItemLayerModel.getQuadsForSprite(0xFFFFFFFF, handleSprite, transform));
            }
        }

        if(headSprite != null) {
            if(headMaskSprite != null) {
                quadBuilder.addAll(ItemTextureQuadConverter.convertTexture(transform, headMaskSprite, headSprite, 7.50f/16f, Direction.NORTH, 0xFFFFFFFF, 1));
                quadBuilder.addAll(ItemTextureQuadConverter.convertTexture(transform, headMaskSprite, headSprite, 8.50f/16f, Direction.SOUTH, 0xFFFFFFFF, 1));
            } else {
                quadBuilder.addAll(ItemLayerModel.getQuadsForSprite(0xFFFFFFFF, headSprite, transform));
            }
        }

        TextureAtlasSprite[][] spriteOrder = getSpriteOrder(handleSprite, handleMaskSprite, headSprite, headMaskSprite);

        for (TextureAtlasSprite[] textureAtlasSprites : spriteOrder) {
            StringBuilder printer = new StringBuilder();
            for (TextureAtlasSprite textureAtlasSprite : textureAtlasSprites) {
                if(textureAtlasSprite == null) {
                    printer.append(' ');
                } else if (textureAtlasSprite.equals(headSprite)) {
                    printer.append('X');
                } else {
                    printer.append('Y');
                }
            }
            EwotMain.LOGGER.info(printer.toString());
        }

        return new BakedItemModel(quadBuilder.build(), particle, transformMap, ItemOverrideList.EMPTY, transform.isIdentity(), owner.isSideLit());
    }

    private TextureAtlasSprite[][] getSpriteOrder(TextureAtlasSprite handle, TextureAtlasSprite handleMask, TextureAtlasSprite head, TextureAtlasSprite headMask) {
        int handleWidth = handleMask == null ? handle.getWidth() : handle.getWidth() * (handleMask.getWidth() / IntMath.gcd(handle.getWidth(), handleMask.getWidth()));
        int handleHeight = handleMask == null ? handle.getHeight() : handle.getHeight() * (handleMask.getHeight() / IntMath.gcd(handle.getHeight(), handleMask.getHeight()));

        int headWidth = headMask == null ? head.getWidth() : head.getWidth() * (headMask.getWidth() / IntMath.gcd(head.getWidth(), headMask.getWidth()));
        int headHeight = headMask == null ? head.getHeight() : head.getHeight() * (headMask.getHeight() / IntMath.gcd(head.getHeight(), headMask.getHeight()));

        int width = handleWidth * (headWidth / IntMath.gcd(handleWidth, headWidth));
        int height = handleHeight * (headHeight / IntMath.gcd(handleHeight, headHeight));

        //x, y
        TextureAtlasSprite[][] overlaidSprites = new TextureAtlasSprite[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!head.isPixelTransparent(0, x * width / head.getWidth(), y * height / head.getHeight()) && (headMask == null || !headMask.isPixelTransparent(0, x * width / headMask.getWidth(), y * height / headMask.getHeight()))) {
                    overlaidSprites[x][y] = head;
                } else if (!handle.isPixelTransparent(0, x * width / handle.getWidth(), y * height / handle.getHeight()) && (handleMask == null || !handleMask.isPixelTransparent(0, x * width / handleMask.getWidth(), y * height / handleMask.getHeight()))) {
                    overlaidSprites[x][y] = handle;
                } else {
                    overlaidSprites[x][y] = null;
                }
            }
        }

        return overlaidSprites;
    }

    @Override
    public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        ImmutableSet.Builder<Material> builder = ImmutableSet.builder();

        builder.add(owner.resolveTexture("handle"))
               .add(owner.resolveTexture("handle_mask"))
               .add(owner.resolveTexture("head"))
               .add(owner.resolveTexture("head_mask"))
               .add(owner.resolveTexture("particle"));

        return builder.build();
    }

    private static TextureAtlasSprite getSpriteIfExistent(IModelConfiguration owner, Function<Material, TextureAtlasSprite> spriteGetter, String name) {
        Material mat = owner.resolveTexture(name);
        if(MissingTextureSprite.getLocation().equals(mat.getTextureLocation()))
            return null;
        else
            return spriteGetter.apply(mat);
    }

    public enum Loader implements IModelLoader<MaskedToolModel> {
        INSTANCE;

        @Override
        public IResourceType getResourceType() {
            return VanillaResourceType.MODELS;
        }

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager) {
            //The dynamic bucket doesn't do anything here, so I don't think I have to either?

        }

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
            //Nothing here either
        }

        @Override
        public MaskedToolModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            return new MaskedToolModel();
        }
    }
}
