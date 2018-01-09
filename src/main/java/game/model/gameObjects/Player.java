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
    private int SPEED = 3;
    private int acceleration = 1;
    private int JUMP_HEIGHT = 10;
    private int JUMP_WIDTH = 60;
    private float jumper=0;
    private long prevTime = System.currentTimeMillis();
    private GameObject jump;
    private long jumpStarted = 0;
    private long jumpFinished = System.currentTimeMillis();
    private int maxJumpTime = 1000;
    float gravity = 0.5f;           // How strong is gravity
    int yVelocity = 0;
    private boolean isJumping = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = SIZE;
        this.height = SIZE;
        this.drawable = new TexturedSprite(SIZE, SIZE, "mant");
        //this.drawable = new Sprite(SIZE, SIZE, 50, 50, 0);
    }

    @Override
    public void getInput() {
        /*if (jumper > 180) {
            jumper = 0;
        }*/
        if (acceleration > 3) {
            acceleration = 3;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            //move(0, (int) (JUMP_HEIGHT*Math.sin(jumper)));
            if( !isJumping /*&& jumper < 180*/)
            {
                isJumping= true;
                jumpStarted = System.currentTimeMillis();
            }
            if (isJumping && System.currentTimeMillis()-jumpStarted <= maxJumpTime) {
                if (System.currentTimeMillis() - jumpStarted < 1000) {
                    move(0, (int) (SPEED * acceleration));
                } else {
                    move(0, (int)(SPEED*acceleration*-1));
                }
            } else {
                isJumping = false;
            }
            jump = new HorizontalLine((int)this.getX(), (int)this.getY(), SIZE, 255, 255, 255);
        } else {
            isJumping = false;
            jumper=0;
            move(0, -SPEED*acceleration*2);
        }
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
        if (System.currentTimeMillis() - jumpStarted > maxJumpTime) {
            jumpStarted = 0;
        }

        if (!Physics.checkCollision(this, new Rectangle(0, 0, Display.getWidth(), Display.getHeight()))) {
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
}
