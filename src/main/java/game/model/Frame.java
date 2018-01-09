package game.model;

public class Frame {
    private int length;
    private Sprite sprite;

    public Frame(Sprite sprite, int length) {
        this.sprite = sprite;
        this.length = length;
    }

    public boolean render() {
        sprite.render();
        return true;
    }
}
