package OperatingSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LRUPageReplacement {
    public static int pageFaults(int [] pages, int capacity){
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        int faults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (!set.contains(page)) {
                faults++;

                if (set.size() == capacity) {
                    int lru = -1;
                    int minIndex = Integer.MAX_VALUE;

                    for (int key : set) {
                        if (map.get(key) < minIndex) {
                            minIndex = map.get(key);
                            lru = key;
                        }
                    }
                    set.remove(lru);
                    map.remove(lru);
                }

                set.add(page);
            }

            map.put(page, i);
        }

        return faults;
    }
    public static void main(String[] args) {
        int[] pages = {1,2,3,4,1,2,5,1,2,3,4,5};
        int capacity = 3;
        System.out.println("Page Faults = " + pageFaults(pages, capacity));
    }
}
