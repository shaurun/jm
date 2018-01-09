package core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import utils.Color;

public class Window {
    private int width, height;
    private String title;
   // private Color color;

    public Window(String title, int width, int height/*, Color color*/) {
        this.title = title;
        this.width = width;
        this.height = height;
        //this.color = color;
    }

    public Window create() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Display.create();
            Keyboard.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void destroy() {
        Display.destroy();
        Keyboard.destroy();
    }
}
