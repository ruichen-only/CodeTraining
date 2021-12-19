package cache;

import java.util.HashMap;

/**
 * Create by crr at 2020.1.5
 * @param <K>
 * @param <V>
 */
public class LRUCacheByLinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;
    private Entry first;
    private Entry last;
    private HashMap<K, Entry<K, V>> map;

    public LRUCacheByLinkedHashMap(int max_cache_size) {
        MAX_CACHE_SIZE = max_cache_size;
        map = new HashMap<>();
    }

    public void put(K key, V value) {
        Entry entry = getEntry(key);
        if(entry == null) {
            if(map.size() >= MAX_CACHE_SIZE) {
                map.remove(last.key);
                removeLast();
            }
            entry = new Entry();
            entry.key = key;
        }
        entry.value = value;
        moveToFirst(entry);
        map.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if(entry == null) return null;

        moveToFirst(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry entry = getEntry(key);
        if(entry != null) {
            if(entry.pre != null) entry.pre.next = entry.next;
            if(entry.next != null) entry.next.pre = entry.pre;
            if(entry == last) last = entry.pre;
            if(entry == first) first = entry.next;
        }
        map.remove(key);
    }

    private void moveToFirst(Entry entry) {
        if(entry == null) return;

        if(entry.pre != null) entry.pre.next = entry.next;
        if(entry.next != null) entry.next.pre = entry.pre;
        if(entry == last) last = last.pre;

        if(first == null && last == null) {
            first = last = entry;
            return;
        }
        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    private void removeLast() {
        if(last != null) {
            last = last.pre;
            if(last == null) first = null;
            else last.next = null;
        }
    }

    private Entry getEntry(K key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Entry entry = first;
        while (entry != null) {
            sb.append(String.format("%s:%s ", entry.key, entry.value));
            entry = entry.next;
        }
        return sb.toString();
    }

    class Entry<K, V> {
        public Entry pre;
        public Entry next;
        public K key;
        public V value;
    }
}
