import jdk.dynalink.linker.LinkerServices;

import java.util.HashMap;
import java.util.Map;


public class LRUCache {

    class Node{
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key , int val) {
            this.key = key;
            this.val = val;
        }
    }
    private final int capacity;
    private Map<Integer, Node> map;
    public Node head ,tail;

    public LRUCache(int capacity){
        this.capacity= capacity;
        this.map = new HashMap<>();

        head= new Node(0,0);
        tail = new Node (0,0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if(!map.containsKey(key))return -1;
        Node node = map.get(key);
        remove(node);
        insert(node);
        return node.val;
    }

    public void put(int key, int val){
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (map.size() == capacity) {
            remove(tail.prev);
        }
        insert(new Node(key,val));
    }

    public void remove(Node node){
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public void insert(Node node){
        map.put(node.key,node);
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2); // capacity = 2

        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1)); // 10 (kyunki 1 recently used hai)

        cache.put(3, 30); // 2 remove hoga (LRU)
        System.out.println(cache.get(2)); // -1 (kyunki 2 evict ho gaya)

        cache.put(4, 40); // 1 remove hoga
        System.out.println(cache.get(1)); // -1 (kyunki 1 evict ho gaya)
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40
    }
}
