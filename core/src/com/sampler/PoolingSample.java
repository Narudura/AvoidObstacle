package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;


/**
 * Created by jarek on 11-Jan-17.
 */
public class PoolingSample extends SampleBase {

    public static final Logger log = new Logger(PoolingSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(PoolingSample.class);

    private static final float BULLET_ALIVE_TIME = 3f;
    private static final float BULLET_SPAWN_TIME = 1f;

    Array<Bullet> bullets = new Array<Bullet>();
    private float timer;

//    private final Pool<Bullet> bulletPool = Pools.get(Bullet.class, 15);

    private final Pool<Bullet> bulletPool = new Pool<Bullet>() {
        @Override
        protected Bullet newObject() {
            log.debug("New object");

            return new Bullet();
        }


        @Override
        public void free(Bullet object) {
            log.debug("before free object = " + object + " free in pool = " + getFree());
            super.free(object);
            log.debug("before free object = " + object + " free in pool = " + getFree());
        }

        @Override
        public Bullet obtain() {

            log.debug("before obtain free = " + getFree());
            Bullet ret = super.obtain();
            log.debug("after obtain free objects = " + getFree());
            return ret;
        }

        @Override
        protected void reset(Bullet object) {
            log.debug("Resetting objects = " + object);
            super.reset(object);
        }
    };

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);


    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        timer+=delta;

        if(timer>BULLET_SPAWN_TIME){
            timer = 0;
            Bullet bullet = bulletPool.obtain();
            bullets.add(bullet);

            log.debug("Alive bullets = " + bullets.size);
        }

        for(int i =0; i<bullets.size; i++){
            Bullet bullet = bullets.get(i);
            bullet.update(delta);

            if(!bullet.alive){
                bullets.removeIndex(i);
                bulletPool.free(bullet);
                log.debug("After free == Alive Bullets = " + bullets.size);

            }


        }



    }

    //Inner class Bullet
    public static class Bullet implements Pool.Poolable {
        boolean alive = true;

        float timer;

        public Bullet() {
        }

        public void update(float delta) {
            timer += delta;

            if (alive && timer > BULLET_ALIVE_TIME){

                alive = false;
            }

        }

        @Override
        public void reset() {
            alive = true;
            timer = 0;

        }
    }

    @Override
    public void dispose() {

        bulletPool.freeAll(bullets);
        bulletPool.clear();
        bullets.clear();

    }
}
