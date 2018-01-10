package game.model.gameObjects;

import game.Application;
import game.Level;
import game.engine.Physics;
import game.model.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.util.List;

public class Player extends GameObject {

    public static final int SIZE = 32;
    private int SPEED = 2;
    private int acceleration = 1;
    private long prevTime = System.currentTimeMillis();
    private GameObject jump;


    private boolean isJumping = false;
    private boolean jumpStarted = false;

    float yVel = -10;
    float gravity = 0.6f;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = SIZE;
        this.height = SIZE;
        this.drawable = new TexturedSprite(SIZE, SIZE, "mant");
    }

    @Override
    public void getInput() {
        if (acceleration > 3) {
            acceleration = 3;
        }

        if (isTouchingGround()) {
            isJumping =false;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            if (isTouchingGround()) {
                isJumping = true;
                yVel = 10;
            }
            if (isJumping) {
                yVel -= gravity;
                jump = new HorizontalLine((int)this.getX(), (int)this.getY(), SIZE, 255, 255, 255);
            }
        } else {
            yVel = -10;

        }
        move(0, (int) yVel);
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            move(-SPEED*acceleration, 0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            move(SPEED*acceleration, 0);
        }
        if (System.currentTimeMillis() - prevTime > 100) {
            acceleration++;
            prevTime = System.currentTimeMillis();
        }

        if (this.getX() < 0) {
            this.setX(Display.getWidth()-SIZE);
        }
        if (this.getX() > Display.getWidth()) {
            this.setX(0);
        }
        if (this.getY() < 0 || this.getY() > Display.getHeight()) {
            Application.restart();
        }
    }

    private void move(int rightShift, int upShift) {
            this.x += rightShift;
            this.y += upShift;
            boolean canMove = true;
            List<GameObject> gameObjects = Application.getGame().getGameObjects();
            for (GameObject go : gameObjects) {
                if (go instanceof Movable && Physics.checkCollision(this, go)) {
                    ((Movable) go).move(rightShift, upShift);
                }
                if (go instanceof Solid && Physics.checkCollision(this, go)) {
                    if (go instanceof NoJumpLine && jump != null &&
                            jump.getX() >= go.getX()) {
                        go.kill();
                    }
                     canMove = false;
                }
                if (go instanceof Door && Physics.checkCollision(this, go)) {
                    canMove = false;
                    for (GameObject go1 : gameObjects) {
                        go1.kill();
                    }
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

    private boolean isTouchingGround() {
        int y = (int) this.getY();
        move(0, -1);
        boolean result = y == (int) this.getY();
        if (!result) {
            move(0, 1);
        }
        return result;
    }

}
