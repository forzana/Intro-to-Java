package PartI;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<V> {
	private LinkedHashMap<Integer, V> map;
	
	public LRUCache(int capacity) {
		// Found how to implement removeEldestEntry for LinkedHashMap here: https://www.geeksforgeeks.org/java/linkedhashmap-removeeldestentry-method-in-java/
		this.map = new LinkedHashMap<Integer, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, V> eldest) {
                return size() > capacity;
            }
        };
	}
	
	public void put(Integer k, V value) {
		map.put(k, value);
	}
	
	public V get(Integer k) {
		return map.get(k);
	}
	
	public String toString() {
		return map.toString();
	}

	// Testing
    public static void main(String[] args) {
    	
    	LRUCache<String> lru = new LRUCache<String>(5);
    	
    	lru.put(0, "Alpha");
    	lru.put(1, "Bravo");
    	lru.put(2, "Charlie");
    	lru.put(3, "Delta");
    	lru.put(4, "Echo");
        System.out.println(lru);
        
    	lru.put(5, "Foxtrot");
    	lru.put(0, "Alice");
        System.out.println(lru);

        lru.put(6, "Golf");
        System.out.println(lru);

        lru.put(5, "Forzana");
        System.out.println(lru);
    }
	
}