package game.model.gameObjects;

import game.model.GameObject;
import game.model.Solid;
import game.model.Sprite;

public class HorizontalLine extends GameObject implements Solid {
    public HorizontalLine(int x, int y, float width, int r, int g, int b) {
        this.width = width;
        this.height = 1;
        this.x = x;
        this.y = y;
        this.drawable = new Sprite(width, height, r, g, b);
    }

    public HorizontalLine(int x, int y, float width, int r, int g, int b, int a) {
        this.width = width;
        this.height = 1;
        this.x = x;
        this.y = y;
        this.drawable = new Sprite(width, height, r, g, b, a);
    }
}
