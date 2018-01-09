package game.model.gameObjects;

import game.model.GameObject;
import game.model.Sprite;
import game.model.TexturedSprite;

public class Window extends GameObject {

    public Window(int x, int y) {
        this.width = 64;
        this.height = 64;
        this.x = x;
        this.y = y;
        this.drawable = new TexturedSprite(width, height, "window");
    }
}
