package game.model;

import static org.lwjgl.opengl.GL11.*;

public class Sprite implements Drawable {
    private int r,g,b,a;
    private float width, height;

    public Sprite(float width, float height, int r, int g, int b) {
        this.width = width;
        this.height = height;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1;
    }

    public Sprite(float width, float height, int r, int g, int b, int a) {
        this.width = width;
        this.height = height;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void render() {
        glColor4f(r, g, b, a);
        glBegin(GL_QUADS);
        {
            glVertex2d(0, 0);
            glVertex2f(0, height);
            glVertex2f(width, height);
            glVertex2f(width, 0);
        }
        glEnd();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
