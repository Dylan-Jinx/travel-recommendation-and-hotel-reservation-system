package se.xmut.trahrs.manager.cache.service.impl;

import se.xmut.trahrs.manager.cache.service.Cache;

import java.util.Deque;
import java.util.LinkedList;

public class FifoCache implements Cache {
    /**组合Cache接口*/
    private Cache cache;
    /**借助此对象存储key的添加顺序*/
    private Deque<Object> keyOrderList;
    /**最大容量*/
    private int maxCap;
    public FifoCache(int maxCap, Cache cache) {
        this.maxCap=maxCap;
        this.cache=cache;
        keyOrderList=new LinkedList<Object>();
    }
    @Override
    public void putObject(Object key, Object value) {
        //1.记录key
        keyOrderList.addLast(key);
        //2.移除老元素
        if(keyOrderList.size()>maxCap) {
            Object oldKey=
                    keyOrderList.removeFirst();
            cache.removeObject(oldKey);
        }
        //3.放新元素
        cache.putObject(key, value);
    }
    @Override
    public Object getObject(Object key) {
        return cache.getObject(key);
    }
    @Override
    public Object removeObject(Object key) {
        return cache.removeObject(key);
    }
    @Override
    public String toString() {
        return cache.toString();
    }
}
