package com.sampler.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;

/**
 * Created by jarek on 03-Jan-17.
 */
public class SampleFactory {

    public static SampleBase newSample(String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Name is required");

        }

        SampleInfo info = SampleInfos.find(name);

        try {
            return (SampleBase) ClassReflection.newInstance(info.getClazz());
        } catch (Exception e) {
            throw new RuntimeException("Connot create sample with name= " + name, e);
        }


    }

    private SampleFactory() {
    }
}
