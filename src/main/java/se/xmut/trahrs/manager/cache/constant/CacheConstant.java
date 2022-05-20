package se.xmut.trahrs.manager.cache.constant;

public class CacheConstant {
    //默认初始容量
    public static final int MAX_CAP = 32;

    //一分钟
    public static final long ONE_MINUTES = 1000 * 60L;

    //十分钟
    public static final long TEN_MINUTES = ONE_MINUTES * 10L;

    //半小时
    public static final long HALF_AN_HOUR = ONE_MINUTES * 30L;

    //一小时
    public static final long ONE_HOUR = ONE_MINUTES * 60L;

    //六小时
    public static final long SIX_HOUR = ONE_HOUR * 6L;

    //一天
    public static final long ONE_DAY = ONE_HOUR * 12L;


    //默认时间（五分钟）
    public static final long DEFAULT_TIME = ONE_MINUTES * 5L;

    public static final String FIFO="fifo";

    public static final String LRU="lru";

    public static final String LFU="lfu";
}
