package game.model;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject {
    protected float x, y;
    protected float width, height;
    protected Drawable drawable;
    protected boolean toKill = false;

    public void getInput() {

    }

    public void update() {

    }

    public void render() {
        glPushMatrix();
        {
            glTranslatef(x, y, 0);
            drawable.render();
        }
        glPopMatrix();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void kill() {
        toKill = true;
    }

    public boolean isToKill() {
        return toKill;
    }
}
