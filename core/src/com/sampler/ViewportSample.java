package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.*;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

/**
 * Created by jarek on 03-Jan-17.
 */
public class ViewportSample extends SampleBase {

    private static final Logger log = new Logger(ViewportSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ViewportSample.class);

    public static final float WORLD_WIDTH = 800.0f;
    public static final float WORLD_HEIGHT = 600.0F;


    private static OrthographicCamera camera;
    private static Viewport viewport;
    private static SpriteBatch batch;
    private static Texture texture;
    private static BitmapFont font;

    ArrayMap<String, Viewport> viewportArrayMap = new ArrayMap<>();

    private static String currentViewportName;
    public static int currentViewportIndex;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Gdx.input.setInputProcessor(this);

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        texture = new Texture(Gdx.files.internal("raw/level-bg-small.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));
        batch = new SpriteBatch();


        createViewports();
        selectNextViewport();



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

        batch.draw(texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        font.draw(batch, currentViewportName, 50, 100);


    }

    private void createViewports() {

        viewportArrayMap.put(StretchViewport.class.getSimpleName(),
                new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));

        viewportArrayMap.put(FitViewport.class.getSimpleName(),
                new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));

        viewportArrayMap.put(FillViewport.class.getSimpleName(),
                new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));

        viewportArrayMap.put(ScreenViewport.class.getSimpleName(),
                new ScreenViewport(camera));

        viewportArrayMap.put(ExtendViewport.class.getSimpleName(),
                new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));

        currentViewportIndex = -1;


    }

    private void selectNextViewport() {

        currentViewportIndex = (currentViewportIndex + 1) % viewportArrayMap.size;


        viewport = viewportArrayMap.getValueAt(currentViewportIndex);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentViewportName = viewportArrayMap.getKeyAt(currentViewportIndex);


    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);


    }

    @Override
    public void dispose() {

        texture.dispose();
        batch.dispose();
        font.dispose();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        selectNextViewport();

        return true;
    }
}
