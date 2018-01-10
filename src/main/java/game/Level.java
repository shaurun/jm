package game;

import game.model.GameObject;
import game.model.gameObjects.*;
import org.lwjgl.opengl.Display;

import java.util.LinkedList;
import java.util.List;

public enum Level {
    THE_BOSS {
        @Override
        void init() {
            gameObjects.add(new Boss(WIDTH-200, HEIGHT-200));
            gameObjects.add(new HorizontalLine(-10, 99, WIDTH+20, 0, 0, 0));
            gameObjects.add(new VerticalLine(-1, 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new VerticalLine(Display.getWidth(), 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new Player(100, 100));
            //gameObjects.add(new Bullet(100, (int) 100));
        }
    },
    STRAIGHT {
        @Override
        void init() {
            gameObjects.add(new Background(0, 0, "1"));
            gameObjects.add(new Door(WIDTH-100, 100));
            gameObjects.add(new HorizontalLine(-10, 99, WIDTH+20, 0, 0, 0));
            gameObjects.add(new VerticalLine(-1, 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new VerticalLine(Display.getWidth(), 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new Player(100, 100));
        }
    },
    NO_JUMPING {
        @Override
        void init() {
            gameObjects.add(new Background(0, 0, "2"));
            float part = WIDTH/3;
            gameObjects.add(new HorizontalLine(-10, 99, part-20+10, 0, 0, 0));
            gameObjects.add(new NoJumpLine((int) part-20, 99, part+40, 255, 255, 255, 0));
            gameObjects.add(new HorizontalLine((int) (2*part+20), 99, part-20+10, 0, 0, 0));
            gameObjects.add(new VerticalLine(-1, 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new VerticalLine(WIDTH, 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new Door(WIDTH-100, 100));
            gameObjects.add(new Player(100, 100));
        }
    },
    GO_BACK {
        @Override
        void init() {
            gameObjects.add(new Background(0, 0, "3"));
            int part = WIDTH/3;
            int h = 300;
            gameObjects.add(new Door(WIDTH-300, 100));
            gameObjects.add(new HorizontalLine(0-10, 99, part+10, 0, 0, 0));
            gameObjects.add(new HorizontalLine(part, h+99, 32, 0, 0, 0));
            gameObjects.add(new HorizontalLine(part+32, 99, 2*part-32+10, 0, 0, 0));
            gameObjects.add(new VerticalLine(part, 99, h, 0, 0, 0));
            gameObjects.add(new VerticalLine(part+32, 99, h, 0, 0, 0));
            gameObjects.add(new Player(100, 100));
        }
    },
    THROUGH_THE_WINDOW {
        @Override
        void init() {
            //gameObjects.add(new Background(0, 0, "4"));
            gameObjects.add(new Door(WIDTH-125, HEIGHT-350));
            gameObjects.add(new HorizontalLine(-10, 99, WIDTH-200+10, 0, 0, 0));
            gameObjects.add(new VerticalLine(-1, 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new VerticalLine(WIDTH, 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new VerticalLine(WIDTH-200, 100, HEIGHT-450, 0, 0, 0));
            gameObjects.add(new HorizontalLine(WIDTH-200, HEIGHT-350, 200+10, 0, 0, 0));
            Window window = new Window(WIDTH-75, HEIGHT-250);
            gameObjects.add(window);
            gameObjects.add(new WindowTeleport(300, 150, window));
            gameObjects.add(new Player(100, 100));
        }
    },
    SHAKE_IT {
        @Override
        void init() {
            gameObjects.add(new Background(0, 0, "4"));
            gameObjects.add(new HorizontalLine(-10, 99, WIDTH+20, 0, 0, 0));
            gameObjects.add(new VerticalLine(-1, 0, HEIGHT, 0, 0, 0));
            gameObjects.add(new VerticalLine(WIDTH, 0, HEIGHT, 0, 0, 0));
            Player player = new Player(100, 100);
            gameObjects.add(new Window(300, 100));
            gameObjects.add(player);

        }
    },
    THE_BOX {
        @Override
        void init() {
            gameObjects.add(new Box(400, 221));
            gameObjects.add(new HorizontalLine(150-10, 160, 150+10, 0, 0, 0));
            gameObjects.add(new HorizontalLine(300, 220, 150, 0, 0, 0));
            gameObjects.add(new HorizontalLine(0, 99, WIDTH+10, 0, 0, 0));
            gameObjects.add(new Door(WIDTH-100, 202));
            Player player = new Player(100, 100);
            gameObjects.add(player);
        }
    };

    protected List<GameObject> gameObjects = new LinkedList<>();
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;

    abstract void init();

    public List<GameObject> getInitialGameObjects() {
        init();
        return gameObjects;
    }
}
