package game.model.gameObjects;

import game.model.GameObject;
import game.model.Sprite;
import game.model.TexturedSprite;

public class Door extends GameObject {

    public Door(int x, int y) {
        this.width = 64;
        this.height = 64;
        this.x = x;
        this.y = y;
        this.drawable = new TexturedSprite(width, height, "door");
    }
}
