package com.sampler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;
import javafx.scene.shape.Circle;

/**
 * Created by jarek on 05-Jan-17.
 */
public class ShapeRendererSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ShapeRendererSample.class);

    public static final float WORLD_WIDTH = 40f;
    public static final float WORLD_HEIGHT = 20f;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;


    @Override
    public void create() {

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render() {
        GdxUtils.clearScreen();


        shapeRenderer.setProjectionMatrix(camera.combined);

        if (drawGrid) {

            drawGrid();
        }
        if (drawCircles) {

            drawCircle();
        }
        if (drawRectangles) {

            drawRectangles();
        }
        if (drawPoints) {

            drawPoints();
        }

    }

    private void drawGrid() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);

        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        for (int x = -worldWidth; x < worldHeight; x++) {

            shapeRenderer.line(x, -worldHeight, x, worldHeight);

        }

        for (int y = -worldHeight; y < worldHeight; y++) {

            shapeRenderer.line(-worldWidth, y, worldWidth, y);

        }

        shapeRenderer.setColor(Color.RED);

        shapeRenderer.line(-worldWidth, 0.0f, worldWidth, 0.0f);
        shapeRenderer.line(0.0f, -worldHeight, 0.0f, worldHeight);


        shapeRenderer.end();


    }

    private void drawCircle() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.circle(2.5f, 2.5f, 2, 30);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(0, 0, 1, 30);

        shapeRenderer.end();


    }

    private void drawRectangles() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.BLUE);

        shapeRenderer.rect(-7, -7, 2, 3);
        shapeRenderer.rect(-4, -4, 1, 2);

        shapeRenderer.end();


    }

    private void drawPoints() {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.BROWN);

        shapeRenderer.point(-3, 2, 0);
        shapeRenderer.point(-3, 0, 0);
        shapeRenderer.point(-10, -2, 0);


        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.x(-10, 0, 0.25f);

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {


        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.G) {
            drawGrid = !drawGrid;
        }
        if (keycode == Input.Keys.C) {
            drawCircles = !drawCircles;
        }
        if (keycode == Input.Keys.R) {
            drawRectangles = !drawRectangles;
        }
        if (keycode == Input.Keys.P) {
            drawPoints = !drawPoints;
        }


        return true;


    }
}
