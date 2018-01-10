package game.model.gameObjects;

import game.Application;
import game.engine.Physics;
import game.model.GameObject;
import game.model.Movable;
import game.model.Solid;
import game.model.TexturedSprite;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.util.List;

public class Bullet extends GameObject {
    float vx, vy;

    public Bullet(int x, int y, float vx, float vy) {
        this.width = 10;
        this.height = 10;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.drawable = new TexturedSprite(width, height, "bullett");
    }

    @Override
    public void update() {
        this.move(vx, vy);
    }

    private void move(float rightShift, float upShift) {
        this.x += rightShift;
        this.y += upShift;
        boolean canMove = true;
        List<GameObject> gameObjects = Application.getGame().getGameObjects();
        for (GameObject go : gameObjects) {
            if (go instanceof Player && Physics.checkCollision(this, go)) {
                Application.restart();
            }
        }
        if (!Physics.checkCollision(this,
                new Rectangle(0, 0, Display.getWidth(), Display.getHeight()))) {
            this.kill();
        }
    }
}
