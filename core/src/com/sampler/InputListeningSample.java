package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

public class InputListeningSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputListeningSample.class);

    private static final Logger log = new Logger(InputListeningSample.class.getName(), Logger.DEBUG);

    private static final int MAX_MESSAGES_COUNT = 15;

    private final Array<String> messages = new Array<String>();


    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;


    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

//        Gdx.input.setInputProcessor(new InputAdapter() {
//
//            @Override
//            public boolean keyDown(int keycode) {
//
//                log.debug("KeyDown keycode= " + keycode);
//                return true;
//            }
//        });

//        Gdx.input.setInputProcessor(this);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        InputAdapter firstProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                log.debug("First: keyDown keycode: " + keycode);

                return true;

            }

            @Override
            public boolean keyUp(int keycode) {
                log.debug("First: keyUp keycode: " + keycode);

                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                log.debug("First: keyTyped char: " + character);

                return false;
            }
        };
        InputAdapter secondProcessor = new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {
                log.debug("Second: keyDown keycode: " + keycode);

                return true;

            }

            @Override
            public boolean keyUp(int keycode) {
                log.debug("Second: keyUp keycode: " + keycode);

                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                log.debug("Second: keyTyped char: " + character);

                return false;
            }
        };

        inputMultiplexer.addProcessor(firstProcessor);
        inputMultiplexer.addProcessor(secondProcessor);

        Gdx.input.setInputProcessor(this);


    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);


    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        draw();

        batch.end();

    }

    private void draw() {

        for (int i = 0; i < messages.size; i++) {

            font.draw(batch,
                    messages.get(i),
                    20f,
                    720 - 40f * (i + 1));

        }


    }

    private void addMessage(String message) {
        messages.add(message);

        if (messages.size > MAX_MESSAGES_COUNT) {

            messages.removeIndex(0);

        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

        batch.dispose();
        font.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {

        String message = "keyDown keyCode = " + keycode;
        log.debug(message);
        addMessage(message);


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        String message = "keyUp keyCode = " + keycode;
        log.debug(message);
        addMessage(message);


        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        String message = "keyTyped keyCode = " + character;
        log.debug(message);
        addMessage(message);


        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        String message = "TouchDown screenX = " + screenX + " screenY= " + screenY;
        log.debug(message);
        addMessage(message);


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        String message = "TouchUp screenX = " + screenX + " screenY= " + screenY;
        log.debug(message);
        addMessage(message);


        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        String message = "TouchDragged screenX = " + screenX + " screenY= " + screenY;
        log.debug(message);
        addMessage(message);


        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        String message = "mouseMoved screenX = " + screenX + " screenY= " + screenY;
        log.debug(message);
        addMessage(message);


        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        String message = "amount = " + amount;
        log.debug(message);
        addMessage(message);


        return true;
    }
}
