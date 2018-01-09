package core;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Sound extends Thread {
    private String file = "C:\\Users\\User\\IdeaProjects\\James\\src\\main\\resources\\audio\\background1.mp3";
    private volatile boolean play = false;

    public Sound() { }

    public void play() {
        this.play = true;
        this.start();
    }

    public void terminate() {
        this.play = false;
    }

    @Override
    public void run() {
        try {
        FileInputStream fis = new FileInputStream(file);
        Player playMP3 = new Player(fis);
        while (!isInterrupted()) {
                if (this.play) {
                    playMP3.play(1);
                    if (playMP3.isComplete()) {
                        this.run();
                    }
                } else {
                    playMP3.close();
                    Thread.currentThread().interrupt();
                    return;
                }
        }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
