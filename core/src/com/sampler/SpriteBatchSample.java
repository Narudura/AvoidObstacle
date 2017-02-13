package com.sampler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

/**
 * Created by jarek on 04-Jan-17.
 */
public class SpriteBatchSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SpriteBatchSample.class);

    public static final float WORLD_WIDTH = 10.8f;
    public static final float WORLD_HEIGHT = 7.2f;


    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Texture texture;
    private Color oldColor;

    private int width = 1;
    private int height = 1;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();

        oldColor = new Color();

        texture = new Texture(Gdx.files.internal("raw/character.png"));


    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        draw();

        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void draw() {
        batch.draw(texture,
                0, 0,                       //x, y of where it should start to be draw
                width / 2, height / 2,          //originX, originY WORLD UNITS!!
                width, height,              //width, height WORLD UNITS!
                1.0f, 1.0f,                 // scale
                0.0f,                       //rotation
                0, 0,                       //srcX, srcY
                texture.getWidth(), texture.getHeight(),        //srcWidth, srcHeight
                false, false
        );                                  // flipX, flipY


        batch.draw(texture,
                2, 2,
                width / 2, height / 2,
                width, height,
                2.0f, 2.0f,
                0.0f,
                0, 0,
                texture.getWidth(), texture.getHeight(),
                false, false
        );

        oldColor.set(batch.getColor());

        batch.setColor(Color.GREEN);

        batch.draw(texture,
                4, 2,
                width / 2, height / 2,
                width, height,
                1.0f, 1.0f,
                0.0f,
                0, 0,
                texture.getWidth(), texture.getHeight(),
                false, true
        );
        batch.setColor(Color.GREEN);

        batch.setColor(oldColor);
    }


    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();

    }
}
