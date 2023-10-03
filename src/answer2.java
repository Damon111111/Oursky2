import java.util.HashMap;
import java.util.Map;

class CacheEntry {
    int key;
    int value;
    int weight;
    double score;
    CacheEntry prev;
    CacheEntry next;

    public CacheEntry(int key, int value, int weight) {
        this.key = key;
        this.value = value;
        this.weight = weight;
        this.score = 0.0;
        this.prev = null;
        this.next = null;
    }
}

class LRUCache {
    private int capacity;
    private int size;
    private double currentTime;
    private CacheEntry head;
    private CacheEntry tail;
    private Map<Integer, CacheEntry> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.currentTime = 0.0;
        this.head = new CacheEntry(-1, -1, -1);
        this.tail = new CacheEntry(-1, -1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            CacheEntry entry = cache.get(key);
            updateScore(entry);
            moveToHead(entry);
            return entry.value;
        }
        return -1;
    }

    public void put(int key, int value, int weight) {
        if (cache.containsKey(key)) {
            CacheEntry entry = cache.get(key);
            entry.value = value;
            entry.weight = weight;
            updateScore(entry);
            moveToHead(entry);
        } else {
            CacheEntry newEntry = new CacheEntry(key, value, weight);
            if (size < capacity) {
                size++;
            } else {
                evictEntry();
            }
            cache.put(key, newEntry);
            updateScore(newEntry);
            addToHead(newEntry);
        }
    }

    private void updateScore(CacheEntry entry) {
        double deltaTime = currentTime - entry.score;
        double score = entry.weight / (Math.log(deltaTime + 1) + 1);
        entry.score = score;
    }

    private void addToHead(CacheEntry entry) {
        entry.prev = head;
        entry.next = head.next;
        head.next.prev = entry;
        head.next = entry;
    }

    private void removeEntry(CacheEntry entry) {
        entry.prev.next = entry.next;
        entry.next.prev = entry.prev;
    }

    private void moveToHead(CacheEntry entry) {
        removeEntry(entry);
        addToHead(entry);
    }

    private void evictEntry() {
        CacheEntry leastScoreEntry = tail.prev;
        removeEntry(leastScoreEntry);
        cache.remove(leastScoreEntry.key);
    }
}

public class answer2 {
    public static void main(String[] args) {
        // 在这里编写测试代码，例如创建一个 LRUCache 对象并调用其方法进行测试
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1, 1);
        cache.put(2, 2, 1);
        System.out.println(cache.get(1));  // Output: 1
        cache.put(3, 3, 1);
        System.out.println(cache.get(2));  // Output: -1
        cache.put(4, 4, 1);
        System.out.println(cache.get(1));  // Output: -1
        System.out.println(cache.get(3));  // Output: 3
        System.out.println(cache.get(4));  // Output: 4
    }
}