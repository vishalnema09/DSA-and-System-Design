package OperatingSystem;

import java.util.*;

public class DynamicMedian {

    public static List<Double> processQueries(List<String> queries){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<Double> result = new ArrayList<>();

        for(String line : queries ) {
            String[] parts = line.split(" ");
            String type = parts[0];

            if(type.equals("add")) {
                int v = Integer.parseInt(parts[1]);

                if (maxHeap.isEmpty() || v <= maxHeap.peek()) {
                    maxHeap.add(v);
                } else {
                    minHeap.add(v);
                }
                if(maxHeap.size() > minHeap.size() + 1) {
                    minHeap.add(maxHeap.poll());
                } else if(minHeap.size() > maxHeap.size()) {
                    maxHeap.add(minHeap.poll());
                }
            }
            else if(type.equals("get")) {
                double median;
                if(maxHeap.size() == minHeap.size()) {
                    median = (maxHeap.peek() + minHeap.peek()) / 2.0;
                } else {
                    median = maxHeap.peek();
                }
                result.add(median);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine();

        List<String> queries = new ArrayList<>();
        for(int i=0;i<k;i++){
            queries.add(sc.nextLine());
        }

        List<Double> medians = processQueries(queries);

        for(double m : medians){
            System.out.printf("%.1f\n", m);
        }

        sc.close();
    }
}
