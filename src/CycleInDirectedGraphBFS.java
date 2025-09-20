import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleInDirectedGraphBFS {


    public static boolean hasCycle(int V, List<List<Integer>> adj){
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;

            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return count != V;
    }
    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Adding directed edges
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0); // cycle here
        adj.get(2).add(3);

        boolean result = hasCycle(V, adj);
        System.out.println("Does graph contain cycle? " + result);
    }
}
