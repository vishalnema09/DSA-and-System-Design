public class NumberOfIslands {
    public static int numIslands(char[][]grid){
        if(grid==null || grid.length ==0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;

        boolean[][]visited = new boolean[row][col];


        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    private static void dfs(char[][] grid, int i, int j , boolean[][]visited){
        int row = grid.length;
        int col = grid[0].length;

        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;


        dfs(grid, i + 1, j, visited);
        dfs(grid, i - 1, j, visited);
        dfs(grid, i, j + 1, visited);
        dfs(grid, i, j - 1, visited);
    }
    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println("Number of Islands = " + numIslands(grid));
    }
}
