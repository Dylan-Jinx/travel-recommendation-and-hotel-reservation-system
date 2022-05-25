package se.xmut.trahrs.service.impl;

import com.google.common.base.Preconditions;
import org.springframework.data.redis.core.RedisTemplate;
import se.xmut.trahrs.util.BloomFilterUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author breeze
 * @date 2022/5/25 21:22
 */
public class BloomFilterRedisServiceImpl<T> {

    private RedisTemplate<String, String> redisTemplate;

    private BloomFilterUtils<T> bloomFilterUtils;

    public void setBloomFilterHelper(BloomFilterUtils<T> bloomFilterUtils) {
        this.bloomFilterUtils = bloomFilterUtils;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据给定的布隆过滤器添加值
     * @param key 用户id 非uuid
     * @param value 看过推荐的景点
     */
    public void addByBloomFilter(String key, T value, Long expireTime) {
        Preconditions.checkArgument(bloomFilterUtils != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterUtils.murmurHashOffset(value);
        for (int i : offset) {
            redisTemplate.opsForValue().setBit(key, i, true);
        }

        //返回过期时长/s 为-1没设置过期时间 为-2 key不存在
        long expire = redisTemplate.opsForValue().getOperations().getExpire(key);
        if(expire==-1){
            if(expireTime!=null){
                redisTemplate.expire(key, expireTime, TimeUnit.DAYS);
            }else {
                redisTemplate.expire(key, 7, TimeUnit.DAYS);
            }

        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     * @param key 用户id，非uuid
     * @param value 看过推荐的景点
     * @return 是否存在
     */
    public boolean includeByBloomFilter(String key, T value) {
        Preconditions.checkArgument(bloomFilterUtils != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterUtils.murmurHashOffset(value);
        for (int i : offset) {
            if (!redisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }
        return true;
    }

}


