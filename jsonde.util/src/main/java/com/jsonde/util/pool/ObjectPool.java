package com.jsonde.util.pool;

import java.lang.ref.SoftReference;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class ObjectPool<T> {

    private final Class<T> elementsClass;
    private final Queue<SoftReference<T>> pool = new LinkedList<SoftReference<T>>();
    private final Queue<T> persistentPool = new ArrayBlockingQueue<T>(4000);

    @SuppressWarnings("unchecked")
    protected ObjectPool() {

        ParameterizedType genericSuperClass = (ParameterizedType)
                getClass().getGenericSuperclass();

        elementsClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];

    }

    public ObjectPool(Class<T> elementsClass) {
        this.elementsClass = elementsClass;
    }

    public final void returnToPool(T element) {
    	synchronized (this){
    		passivate(element);
            if (!persistentPool.offer(element) && pool.size() < 4000) {
                pool.add(new SoftReference<T>(element));
            }
    	}
    }

    protected void passivate() {
    }

    protected void activate() {
    }

    protected T create() throws ObjectPoolException {
        try {
            return elementsClass.newInstance();
        } catch (InstantiationException e) {
            throw new ObjectPoolException(e);
        } catch (IllegalAccessException e) {
            throw new ObjectPoolException(e);
        }
    }

    public final T takeFromPool() throws ObjectPoolException {
    	synchronized (this){

            T element = takeFromPoolOrCreateImpl();
            activate(element);
            return element;
    	}

    }

    private T takeFromPoolOrCreateImpl() throws ObjectPoolException {
        T element;

        if (null != (element = persistentPool.poll())) {
            return element;
        }

        while (!pool.isEmpty()) {
            SoftReference<T> elementReference = pool.poll();
            if (null != (element = elementReference.get())) {
                activate(element);
                return element;
            }
        }

        return create();
    }

}
