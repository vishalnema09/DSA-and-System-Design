import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphCycleDetection {
    static class Graph{
        int V;
        List<List<Integer>> adj;
        Graph(int V){
            this.V = V;
            adj = new ArrayList<>();
            for (int i =0;i<V;i++){
                adj.add(new ArrayList<>());
            }
        }
        void addEdge(int u , int v){
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        boolean isCyclic(){
            boolean []visited = new boolean[V];
            for (int i = 0; i < V; i++) {
                if(!visited[i]){
                    if(bfsCheckCycle(i,visited)){
                        return true;
                    }
                }
            }
            return false;
        }
        private boolean bfsCheckCycle(int start, boolean[] visited) {
            Queue<int[]> queue = new LinkedList<>(); // store {node, parent}
            queue.offer(new int[]{start, -1});
            visited[start] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int node = current[0];
                int parent = current[1];

                for (int neighbor : adj.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(new int[]{neighbor, node});
                    } else if (neighbor != parent) {
                        // visited neighbor but not parent → cycle
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        System.out.println("Graph 1 has cycle? " + g1.isCyclic()); // false

        Graph g2 = new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);
        System.out.println("Graph 2 has cycle? " + g2.isCyclic()); // true
    }
}
