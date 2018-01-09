package game.model.gameObjects;

import game.model.GameObject;
import game.model.Solid;
import game.model.Sprite;

public class VerticalLine extends GameObject implements Solid {
    public VerticalLine(int x, int y, float height, int r, int g, int b) {
        this.width = 1;
        this.height = height;
        this.x = x;
        this.y = y;
        this.drawable = new Sprite(width, height, r, g, b);
    }
}
