package game.model;

import java.util.ArrayList;

public class Animation implements Drawable {
    private ArrayList<Frame> frames;
    private int currentFrame = 0;

    public Animation() {
        frames = new ArrayList<Frame>();
    }

    @Override
    public void render() {
        if (frames.get(currentFrame).render()) {
            currentFrame++;
            currentFrame %= frames.size();
        }
    }
}
