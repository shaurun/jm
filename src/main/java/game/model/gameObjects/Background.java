package game.model.gameObjects;

import game.model.GameObject;
import game.model.TexturedSprite;

public class Background extends GameObject {
    public Background(int x, int y, String fileName) {
        this.x = x;
        this.y = y;
        this.drawable = new TexturedSprite(800, 600, fileName);
    }
}
