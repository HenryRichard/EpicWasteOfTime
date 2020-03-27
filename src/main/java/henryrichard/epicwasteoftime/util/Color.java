package henryrichard.epicwasteoftime.util;

public class Color {

    //Constants
    public static final Color ENDITE_BOTTOM = new Color(0x00FFE4);
    public static final Color ENDITE_TOP = new Color(0x00D2FF);

    public final int red;
    public final int green;
    public final int blue;

    public Color(int rgb) {
        this((rgb & 0xFF0000) >> 16, (rgb & 0xFF00) >> 8, rgb & 0xFF);
    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getHex() {
        return (red << 16) + (green << 8) + blue;
    }

    public static Color interpolate(Color x, Color y, int i) {
        int r, g, b;
        r = (x.red * i + y.red * (255 - i)) / 255;
        g = (x.green * i + y.green * (255 - i)) / 255;
        b = (x.blue * i + y.blue * (255 - i)) / 255;
        return new Color(r, g, b);
    }
}
