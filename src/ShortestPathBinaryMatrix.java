import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrix {

    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};


    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // {x,y,steps}
        grid[0][0] = 1; // mark visited

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1], steps = cell[2];

            if (x == n - 1 && y == n - 1) return steps;

            for (int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && grid[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny, steps + 1});
                    grid[nx][ny] = 1; // mark visited
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        int[][] grid = {
                {0,1},
                {1,0}
        };

        int result = shortestPathBinaryMatrix(grid);
        System.out.println("Shortest Path Length: " + result);
    }
}
