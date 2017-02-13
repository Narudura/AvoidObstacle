package com.sampler.common;



import com.sampler.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jarek on 03-Jan-17.
 */
public class SampleInfos {

    public static final List<SampleInfo> ALL = Arrays.asList(
            ApplicationListenerSampler.SAMPLE_INFO,
            GdxModuleInfoSample.SAMPLE_INFO,
            GdxReflectionSample.SAMPLE_INFO,
            GdxSamplerGame.SAMPLE_INFO,
            InputListeningSample.SAMPLE_INFO,
            InputPollingSample.SAMPLE_INFO,
            OrthographicCameraSample.SAMPLE_INFO,
            ViewportSample.SAMPLE_INFO,
            SpriteBatchSample.SAMPLE_INFO,
            ShapeRendererSample.SAMPLE_INFO,
            BitmapFontSample.SAMPLE_INFO,
            PoolingSample.SAMPLE_INFO

    );

    private SampleInfos() {


    }

    public static List<String> getSampleNames() {
        List<String> ret = new ArrayList<>();

        for (SampleInfo info : ALL) {
            ret.add(info.getName());
        }

        Collections.sort(ret);
        return ret;

    }

    public static SampleInfo find(String name) {

        if (name == null || name.isEmpty()) {

            throw new IllegalArgumentException("Name Required!");

        }

        SampleInfo ret = null;

        for (SampleInfo info : ALL) {

            if (info.getName().equals(name)) {
                ret = info;
                break;
            }

        }

        return ret;

    }


}
