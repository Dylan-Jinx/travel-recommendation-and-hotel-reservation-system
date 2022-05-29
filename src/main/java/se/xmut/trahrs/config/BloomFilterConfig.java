package se.xmut.trahrs.config;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import se.xmut.trahrs.service.impl.BloomFilterRedisServiceImpl;
import se.xmut.trahrs.util.BloomFilterUtils;

/**
 * @author breeze
 * @date 2022/5/25 21:30
 */
@Configuration
public class BloomFilterConfig {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    public BloomFilterUtils<String> initBloomFilterHelper() {
        return new BloomFilterUtils<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
                .putString(from, Charsets.UTF_8), 1000000, 0.01);
    }

    /**
     * 布隆过滤器bean注入
     * @return 布隆过滤器
     */
    @Bean
    public BloomFilterRedisServiceImpl<String> bloomRedisService(){
        BloomFilterRedisServiceImpl<String> bloomFilterRedisService = new BloomFilterRedisServiceImpl<>();
        bloomFilterRedisService.setBloomFilterHelper(initBloomFilterHelper());
        bloomFilterRedisService.setRedisTemplate(redisTemplate);
        return bloomFilterRedisService;
    }

}


