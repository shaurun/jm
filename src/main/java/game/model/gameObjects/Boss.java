package game.model.gameObjects;

import game.Application;
import game.model.GameObject;
import game.model.TexturedSprite;

public class Boss extends GameObject {

    private long prevTime;
    private long prevTime2;

    public Boss(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.drawable = new TexturedSprite(64, 64, "boss");
        prevTime = prevTime2 = System.currentTimeMillis();
    }

    @Override
    public void update() {
        long curTime = System.currentTimeMillis();
        if (curTime - prevTime > 1500) {
            prevTime = curTime;
            Application.getGame().add(new Bullet((int) this.getX()-16, (int) this.getY(), -1.2f, -1/2f));
            Application.getGame().add(new Bullet((int) this.getX()-16, (int) this.getY()-16, -1, -1f));
            Application.getGame().add(new Bullet((int) this.getX()-16, (int) this.getY()+16, -1.4f, 0));
        }
        if (curTime - prevTime2 > 1500) {
            prevTime2 = curTime;

        }
    }
}
