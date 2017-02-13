package com.sampler.common;

/**
 * Created by jarek on 03-Jan-17.
 */
public class SampleInfo {

    private final String name;
    private final Class<? extends SampleBase> clazz;

    public SampleInfo(Class<? extends SampleBase> clazz){

        this.clazz = clazz;
        name = clazz.getSimpleName();
    }

    public String getName() {
        return name;
    }

    public Class<? extends SampleBase> getClazz() {
        return clazz;
    }
}
