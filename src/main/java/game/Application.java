package game;

import core.Sound;
import core.Window;
import game.Game;
import game.model.GameObject;
import org.lwjgl.opengl.Display;

import java.util.Arrays;
import java.util.Iterator;

import static org.lwjgl.opengl.GL11.*;

public class Application {
    private static Window window = new Window("James", 800, 600);
    private static Game game;
    private static Iterator<Level> i = Arrays.asList(Level.values()).iterator();

    public static void main(String[] args) {
       window.create();
       initializeGraphics();
       Sound s = new Sound();
       s.play();
       newGame();
       gameLoop();
       cleanUp();
       s.terminate();
    }

    private static void initGame() {
        if (i.hasNext()) {
            game = new Game(i.next());
        }
    }

    private static void gameLoop() {
        while (!Display.isCloseRequested()) {
            getInput();
            update();
            render();
        }
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        game.render();

        Display.update();
        Display.sync(60);
    }

    private static void update() {
        game.update();
        if (game.getGameObjects().isEmpty()) {
            newGame();
        }
    }

    private static void initializeGraphics() {
        /*glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);

        glDisable(GL_DEPTH_TEST);
        glClearColor(255, 255, 255, 0);
        glClear(GL_COLOR_BUFFER_BIT);*/
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);
        glClear(GL_DEPTH_BUFFER_BIT);
        glClearColor(255, 255, 255, 0);

    }

    public static void getInput() {
        game.getInput();
    }

    public static void cleanUp() {
        window.destroy();
    }

    public static Game getGame() {
        return game;
    }

    public static void newGame() {
        initGame();
    }

    public static void restart() {
        Level level = game.getLevel();
        for (GameObject go1 : Application.getGame().getGameObjects()) {
            go1.kill();
        }
        game = null;
        game = new Game(level);
    }
}
