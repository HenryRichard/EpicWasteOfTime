package henryrichard.epicwasteoftime.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.List;

@SuppressWarnings("deprecation")
public class SlimeOreBlock extends Block {

    private final EntityType<? extends SlimeEntity> slimeType;

    public SlimeOreBlock(EntityType<? extends SlimeEntity> slimeType, Block.Properties prop) {
        super(prop);
        this.slimeType = slimeType;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.getBlock() == this || super.isSideInvisible(state, adjacentBlockState, side);
    }

    @Override
    public void spawnAdditionalDrops(BlockState state, World world, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, world, pos, stack);
        if (!world.isRemote && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {

            SlimeEntity entity = slimeType.create(world);
            if(entity != null) {
                entity.setSlimeSize(1, true);

                if (world.getDifficulty() != Difficulty.PEACEFUL) {
                    //spawn the entity
                    entity.setLocationAndAngles(
                            pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D,
                            MathHelper.nextFloat(world.getRandom(), -180f, 179.9f), 0.0F
                    );
                    world.addEntity(entity);
                    entity.spawnExplosionParticle();
                } else {
                    //get entity drops and spawn them in instead
                    //TODO: this doesn't quite work as intended; looting doesn't work
                    if(world.getServer() != null) {
                        int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
                        ItemStack weapon = new ItemStack(Items.IRON_SWORD);
                        EnchantmentHelper.setEnchantments(ImmutableMap.of(Enchantments.LOOTING, fortuneLevel), weapon);
                        FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((ServerWorld) world);
                        fakePlayer.setItemStackToSlot(EquipmentSlotType.MAINHAND, weapon);

                        LootTable loot = world.getServer().getLootTableManager().getLootTableFromLocation(entity.getLootTableResourceLocation());
                        LootContext context = new LootContext.Builder((ServerWorld) world)
                                .withRandom(world.getRandom())
                                .withParameter(LootParameters.THIS_ENTITY, entity)
                                .withParameter(LootParameters.POSITION, pos)
                                .withParameter(LootParameters.DAMAGE_SOURCE, DamageSource.causePlayerDamage(fakePlayer))
                                .build(LootParameterSets.ENTITY);
                        List<ItemStack> drops = loot.generate(context);

                        for (ItemStack drop : drops) {
                            spawnAsEntity(world, pos, drop);
                        }
                    }
                }
            }
        }
    }
}
