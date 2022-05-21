package se.xmut.trahrs.manager.cache.service.impl;

import se.xmut.trahrs.manager.cache.service.Cache;

public class SynchronizedCache implements Cache {
    private Cache cache;

    public SynchronizedCache(Cache cache) {
        this.cache=cache;
    }

    @Override
    public synchronized void putObject(Object key, Object value) {
        cache.putObject(key, value);
    }

    public synchronized Object getObject(Object key) {
        return cache.getObject(key);
    }

    @Override
    public synchronized Object removeObject(Object key) {
        return cache.removeObject(key);
    }
}
