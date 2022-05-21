package se.xmut.trahrs.manager.cache.service.impl;

import se.xmut.trahrs.manager.cache.constant.CacheConstant;
import se.xmut.trahrs.manager.cache.map.ExpiryMap;
import se.xmut.trahrs.manager.cache.service.Cache;

import java.util.Map;

public class ExpiryCache implements Cache {
    private Map<Object, Object> cache=new ExpiryMap<>();

    public ExpiryCache(){
        cache=new ExpiryMap<>(CacheConstant.DEFAULT_TIME);
    }

    public ExpiryCache(Long expiryTime){
        this.cache=new ExpiryMap<>(expiryTime);
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    @Override
    public String toString() {
        return cache.toString();
    }
}
