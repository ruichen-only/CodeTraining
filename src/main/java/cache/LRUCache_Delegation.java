package cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LRUCache_Delegation<K, V> {
    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    LinkedHashMap<K, V> map;

    public LRUCache_Delegation(int max_cache_size) {
        MAX_CACHE_SIZE = max_cache_size;
        //根据max_cache_size和加载因子计算HashMap的capacity，+1确保到达max_cache_size上限是不会触发HashMap的扩容
        int capacity = (int) (Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1);
        map = new LinkedHashMap(capacity, DEFAULT_LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(V key) {
        return map.get(key);
    }

    public synchronized V remove(K key) {
        return map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : getAll()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
