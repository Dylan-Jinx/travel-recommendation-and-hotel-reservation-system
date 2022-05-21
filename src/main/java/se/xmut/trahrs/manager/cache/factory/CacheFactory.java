package se.xmut.trahrs.manager.cache.factory;

import org.springframework.stereotype.Component;
import se.xmut.trahrs.manager.cache.service.Cache;
import se.xmut.trahrs.manager.cache.service.impl.*;
import se.xmut.trahrs.manager.cache.vo.CacheVo;
import se.xmut.trahrs.manager.cache.constant.CacheConstant;
import se.xmut.trahrs.manager.cache.exception.CacheException;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheFactory {
    private final ConcurrentHashMap<String, Cache> cachePools = new ConcurrentHashMap<>();

    //生产缓存
    public Cache produce(CacheVo cacheVo) {
        if(cachePools.containsKey(cacheVo.getCacheName())){
            return cachePools.get(cacheVo.getCacheName());
        }

        //定义缓存
        Cache cache=null;
        //有效期缓存还是永久缓存
        cache=cacheVo.getExpire()?new ExpiryCache(cacheVo.getExpireTime()):new PerpetualCache();

        //缓存淘汰策略
        switch (cacheVo.getType().toLowerCase()){
            case CacheConstant.FIFO:
                cache=new FifoCache(cacheVo.getCapacity(),cache);break;
            case CacheConstant.LRU:
                cache=new LruCache(cacheVo.getCapacity(),cache);break;
            case CacheConstant.LFU:
                cache=new LfuCache(cacheVo.getCapacity(),cache);break;
            default:
                throw new CacheException("请输入fifo，lru，lfu其中一种策略");
        }

        //是否开启日志
        if(cacheVo.getLog())cache=new LogCache(cache);

        //是否开启同步
        if(cacheVo.getSync())cache=new SynchronizedCache(cache);

        cachePools.put(cacheVo.getCacheName(), cache);
        return cache;
    }
}
