package se.xmut.trahrs.manager.cache.annoation;

import org.springframework.core.annotation.AliasFor;
import se.xmut.trahrs.manager.cache.constant.CacheConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Cacheable {
    //缓存名
    @AliasFor("cacheName")
    String value() default "";

    @AliasFor("value")
    String cacheName() default "";

    //缓存key
    String key() default "";

    //缓存策略，有fifo，lru，lfu三种，默认fifo
    String type() default CacheConstant.FIFO;

    //缓存容量，默认32
    int capacity() default CacheConstant.MAX_CAP;

    //是否开启日志
    boolean log() default false;

    //是否同步
    boolean sync() default false;

    //是否开启有效期缓存
    boolean expire() default false;

    //设置有效时长，默认五分钟
    long expireTime() default CacheConstant.DEFAULT_TIME;
}
