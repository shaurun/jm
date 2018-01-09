package game.model.gameObjects;

import game.Application;
import game.engine.Physics;
import game.model.GameObject;

import java.util.List;

public class WindowTeleport extends Window {
    Window toWindow;

    public WindowTeleport(int x, int y, Window toWindow) {
        super(x, y);
        this.toWindow = toWindow;
    }

    @Override
    public void update() {
        List<GameObject> gameObjects = Application.getGame().getGameObjects();
        for (GameObject go : gameObjects) {
            if (go instanceof Player && Physics.checkCollision(this, go)) {
                go.setX(toWindow.getX());
                go.setY(toWindow.getY());
            }
        }
    }
}
