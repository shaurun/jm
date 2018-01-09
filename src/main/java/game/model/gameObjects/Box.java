package game.model.gameObjects;

import game.Application;
import game.engine.Physics;
import game.model.*;

import java.util.List;

public class Box extends GameObject implements Solid, Movable {
    public static final int SIZE = 32;
    private int SPEED = 3;
    private int acceleration = 1;

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = SIZE;
        this.height = SIZE;
        this.drawable = new TexturedSprite(32, 32, "box");
        //this.drawable = new Sprite(SIZE, SIZE, 50, 0, 50);
    }

    @Override
    public void update() {
        move(0, -SPEED*acceleration*2);
    }

    @Override
    public void move(int rightShift, int upShift) {
        this.x += rightShift;
        this.y += upShift;
        boolean canMove = true;
        List<GameObject> gameObjects = Application.getGame().getGameObjects();
        for (GameObject go : gameObjects) {
            if (!this.equals(go) && (go instanceof Solid) && Physics.checkCollision(this, go)) {
                canMove = false;
            }
        }

        if (!canMove) {
            this.x -= rightShift;
            this.y -= upShift;
            if (!(rightShift==0)) {
                move(rightShift > 0 ?  --rightShift : ++rightShift, 0);
            }
            if (!(upShift == 0))  {
                move(0, upShift > 0 ? --upShift : ++upShift);
            }
        }
    }
}
