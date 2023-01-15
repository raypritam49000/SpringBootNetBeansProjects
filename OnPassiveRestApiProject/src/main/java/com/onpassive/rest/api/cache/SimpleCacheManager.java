package com.onpassive.rest.api.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleCacheManager {

    public Map<String, Object> map = new HashMap();
    public Map<String, Object> cache = Collections.synchronizedMap(map);

    public void add(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public int size() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
    }

    public Boolean isContain(String key) {
        return cache.containsKey(key);
    }

    public Boolean isEmpty() {
        return cache.isEmpty();
    }

    public Map<String, Object> getCache() {
        return cache;
    }

    public void setCache(Map<String, Object> cache) {
        this.cache = cache;
    }  
}
