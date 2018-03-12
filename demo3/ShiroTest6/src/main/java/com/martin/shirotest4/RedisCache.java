package com.martin.shirotest4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;


public class RedisCache<K, V> implements Cache<K, V> {

    private static final String REDIS_SHIRO_CACHE = "shiro-cache:";
    private String cacheKey;
    private RedisTemplate<K, V> redisTemplate;
    private long globExpire = 1;

    public RedisCache(String name, RedisTemplate client) {
        this.cacheKey = REDIS_SHIRO_CACHE + name + ":";
        this.redisTemplate = client;
    }

    @Override
    public V get(K key) throws CacheException {
        System.out.println("===============RedisCache.get================"); 
        redisTemplate.boundValueOps(getCacheKey(key)).expire(globExpire, TimeUnit.MINUTES);
        return redisTemplate.boundValueOps(getCacheKey(key)).get();
    }

    @Override
    public V put(K key, V value) throws CacheException {
        System.out.println("===============RedisCache.put================");
        V old = get(key);
        redisTemplate.boundValueOps(getCacheKey(key)).set(value);
        return old;
    }

    @Override
    public V remove(K key) throws CacheException {
        System.out.println("===============RedisCache.remove================");
        V old = get(key);
        redisTemplate.delete(getCacheKey(key));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("===============RedisCache.clear================");
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        System.out.println("===============RedisCache.size================");
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        System.out.println("===============RedisCache.keys================");
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        System.out.println("===============RedisCache.values================");
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private K getCacheKey(Object k) {
        return (K) (this.cacheKey + k);
    }
}
