package se.xmut.trahrs.manager.cache.service;

public interface Cache {
    /**
     * 存储数据
     * @param key
     * @param value
     */
    void putObject(Object key, Object value);
    /**
     * 取数据
     * @param key
     * @return
     */
    Object getObject(Object key);
    /**
     *  基于key移除元素
     * @param key
     * @return
     */
    Object removeObject(Object key);
}
