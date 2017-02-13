package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

/**
 * Created by jarek on 03-Jan-17.
 */
public class OrthographicCameraSample extends SampleBase {

    private static final Logger log = new Logger(OrthographicCameraSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(OrthographicCameraSample.class);

    private static final float WORLD_WIDTH = 10.8f; // world units 1080 / 100 = 10.8 1world unit = 100px
    private static final float WORLD_HEIGHT = 7.2f; // world units 720 / 100 = 7.2 1world unit = 100px

    public static final float CAMERA_SPEED = 2.0f;
    public static final float CAMERA_ZOOM_SPEED = 2.0f;


    private OrthographicCamera camera;
    private Viewport viewport;

    private SpriteBatch batch;

    private Texture texture;


    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg.png"));


    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);


    }

    @Override
    public void render() {

        inputHandling();

        GdxUtils.clearScreen();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        draw();

        batch.end();

    }

    private void draw() {

        batch.draw(texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

    }

    private void inputHandling() {

        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            camera.position.x -= CAMERA_SPEED * deltaTime;

        }if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            camera.position.x += CAMERA_SPEED * deltaTime;

        }if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

            camera.position.y += CAMERA_SPEED * deltaTime;

        }if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

            camera.position.y -= CAMERA_SPEED * deltaTime;

        }if (Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)) {

            camera.zoom -= CAMERA_ZOOM_SPEED * deltaTime;

        }if (Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)) {

            camera.zoom += CAMERA_ZOOM_SPEED * deltaTime;

        }if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

            log.debug("\nCamera position = " + camera.position + "\nzoom = " + camera.zoom);

        }

        camera.update();


    }


    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}

