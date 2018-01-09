package game;

import game.model.GameObject;
import game.model.gameObjects.Door;
import org.lwjgl.opengl.Display;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Level level;
    private List<GameObject> gameObjects;
    private static int startX;

    public Game(Level level) {
        this.level = level;
        this.gameObjects = new LinkedList<>(level.getInitialGameObjects());
        startX = Display.getX();
    }

    public void getInput() {
        for (GameObject go : gameObjects) {
            go.getInput();
        }
    }

    public void update() {
        if (startX != -404 && level.equals(Level.SHAKE_IT) && Display.getX() != startX) {
            startX = -404;
            gameObjects.add(new Door(Display.getWidth()-100, 100));
        }
        for (GameObject go : gameObjects) {
            go.update();
        }
        gameObjects = gameObjects.stream().filter(gameObject -> !gameObject.isToKill()).collect(Collectors.toList());
    }

    public void render() {
        for (GameObject go : gameObjects) {
            go.render();
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Level getLevel() {
        return level;
    }
}
