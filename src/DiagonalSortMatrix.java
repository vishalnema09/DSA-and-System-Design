import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DiagonalSortMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int [][] mat = new int [m][n];

        for (int i =0;i<m;i++){
            for (int j = 0; j < n; j++) {
                mat[i][j]=sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            sortDiagonal(mat, i, 0, m, n);
        }
        for (int j = 1; j < n; j++) {
            sortDiagonal(mat, 0, j, m, n);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
    public static void sortDiagonal(int[][] mat, int row, int col, int m, int n){
        List<Integer> diagonal = new ArrayList<>();
        int r = row ;
        int c = col;
        while (r < m && c < n) {
            diagonal.add(mat[r][c]);
            r++;
            c++;
        }
        Collections.sort(diagonal);
        r = row;
        c = col;
        int idx = 0;

        while (r < m && c < n) {
            mat[r][c] = diagonal.get(idx);
            r++;
            c++;
            idx++;
        }
    }
}
