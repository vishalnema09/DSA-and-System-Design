import java.util.*;

public class BipartiteGraph {


    public static boolean isBipartite(int V, List<List<Integer>>adj){
        int []color = new int[V];
        Arrays.fill(color,-1);
        for(int start=0;start<V;start++){
            if(color[start]==-1){
                Queue<Integer> q = new LinkedList<>();
                q.offer(start);
                color[start]=0;

                while(!q.isEmpty()){
                    int node = q.poll();

                    for(int neighbor:adj.get(node)){
                        if(color[neighbor]==-1){
                            color[neighbor]=1-color[node];
                            q.offer(neighbor);
                        }else if(color[neighbor]==color[node]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i<V;i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(1).add(0);

        adj.get(0).add(3);
        adj.get(3).add(0);

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(3);
        adj.get(3).add(2);

        boolean result = isBipartite(V, adj);
        System.out.println("Is Graph Bipartite? " + result);
    }
}
