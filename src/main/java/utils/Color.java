package utils;

public class Color {
    private short red, green, blue, alpha;

    public Color(short red, short green, short blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha((short) 0);
    }

    public Color(short red, short green, short blue, short alpha) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }

    public short getRed() {
        return red;
    }

    public void setRed(short red) {
        this.red = red;
    }

    public short getGreen() {
        return green;
    }

    public void setGreen(short green) {
        this.green = green;
    }

    public short getBlue() {
        return blue;
    }

    public void setBlue(short blue) {
        this.blue = blue;
    }

    public short getAlpha() {
        return alpha;
    }

    public void setAlpha(short alpha) {
        this.alpha = alpha;
    }
}
