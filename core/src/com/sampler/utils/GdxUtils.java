package com.sampler.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by jarek on 03-Jan-17.
 */
public class GdxUtils {

    public static void clearScreen(){

        clearScreen(Color.BLACK);

    }

    public static void clearScreen(Color color){
        //clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }



    private GdxUtils(){


    }
}
