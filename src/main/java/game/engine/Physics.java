package game.engine;

import game.model.GameObject;

import java.awt.*;

public class Physics {
    public static boolean checkCollision(GameObject go1, GameObject go2) {
        float tw = go1.getWidth();
        float th = go1.getHeight();
        float rw = go2.getWidth();
        float rh = go2.getHeight();
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        float tx = go1.getX();
        float ty = go1.getY();
        float rx = go2.getX();
        float ry = go2.getY();
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public static boolean checkCollision(GameObject go, Rectangle rect) {
        Rectangle r = new Rectangle((int) go.getX(), (int) go.getY(), (int) go.getWidth(), (int) go.getHeight());
        return r.intersects(rect);
    }
}
