package game.model;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL14.GL_TEXTURE_LOD_BIAS;
import static org.lwjgl.opengl.GL14.glBlendFuncSeparate;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.opengl.GL40.glBlendFunci;
import static org.newdawn.slick.opengl.PNGDecoder.RGBA;

public class TexturedSprite implements Drawable {
    Texture texture;
    private float width, height;
    protected String pngFile;

    public TexturedSprite(float width, float height, String pngFile) {
        this.width = width;
        this.height = height;
        this.pngFile = pngFile;
        /*try {
            texture = TextureLoader.getTexture("PNG",
                    new FileInputStream(new File("C:\\Users\\User\\IdeaProjects\\James\\src\\main\\resources\\graphics\\" +pngFile + ".png").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            texture = null;
        } catch (RuntimeException e) {
            e.printStackTrace();
            texture = null;
        }*/

    }

    @Override
    public void render() {
        if (texture == null) {
            try {
                texture = TextureLoader.getTexture("PNG",
                        new FileInputStream(new File("C:\\Users\\User\\IdeaProjects\\James\\src\\main\\resources\\graphics\\" +pngFile + ".png").getPath()));

                glGenerateMipmap(GL_TEXTURE_2D);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
                glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_LOD_BIAS, 0f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        glColor3f(255,255,255);
        texture.bind();
        glEnable(GL_ALPHA_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND); glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        //glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

        glClear(GL_DEPTH_BUFFER_BIT);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 1);
            glVertex2d(0, 0);
            glTexCoord2f(0, 0);
            glVertex2f(0, height);
            glTexCoord2f(1,0);
            glVertex2f(width, height);
            glTexCoord2f(1, 1);
            glVertex2f(width, 0);
        }
        glDisable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);
        glDisable(GL_TEXTURE_2D);
        texture.release();
        glEnd();
    }
}
