import java.util.*;

public class DijkstraAlgorithm {
    static class Pair{
        int node, dist;
        Pair(int node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }
    public static int[]dijkstra(int V, List<List<Pair>> adj, int src){
        int[] dist = new int [V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src]=0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));
        pq.offer(new Pair(src, 0));

        while (!pq.isEmpty()){
            Pair curr = pq.poll();
            int u = curr.node;
            int d = curr.dist;

            if (d > dist[u]) continue;
            for (Pair neighbor : adj.get(u)) {
                int v = neighbor.node;
                int weight = neighbor.dist;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges
        adj.get(0).add(new Pair(1, 4));
        adj.get(0).add(new Pair(2, 1));
        adj.get(2).add(new Pair(1, 2));
        adj.get(1).add(new Pair(3, 1));
        adj.get(2).add(new Pair(3, 5));
        adj.get(3).add(new Pair(4, 3));

        int[] distances = dijkstra(V, adj, 0);

        System.out.println("Shortest distances from source 0: " + Arrays.toString(distances));
    }
}
