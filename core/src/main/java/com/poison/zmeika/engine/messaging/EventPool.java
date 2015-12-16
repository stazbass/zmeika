package com.poison.zmeika.engine.messaging;

import com.poison.zmeika.engine.pool.IObjectFactory;
import com.poison.zmeika.engine.pool.SimpleObjectPool;

/**
 *
 */
public class EventPool {
    class EventFactory implements IObjectFactory<GameEvent>{
        @Override
        public GameEvent createNew() {
            return new GameEvent();
        }
    }

    public GameEvent getEvent(){
        return gameEventPool.get();
    }

    public void releaseEvent(GameEvent event){
        gameEventPool.release(event);
    }

    private SimpleObjectPool<GameEvent> gameEventPool = buildObjectPool();

    private SimpleObjectPool<GameEvent> buildObjectPool(){
        SimpleObjectPool<GameEvent> result = new SimpleObjectPool<GameEvent>(1000);
        result.setFactory(new IObjectFactory<GameEvent>() {
            @Override
            public GameEvent createNew() {
                return new GameEvent();
            }
        });
        return result;
    }

    private static EventPool poolInstance = null;

    public static EventPool instance(){
        if(poolInstance == null){
            poolInstance = new EventPool();
        }
        return poolInstance;
    }
}
