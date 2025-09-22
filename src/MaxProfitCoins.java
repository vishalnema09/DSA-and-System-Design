import java.util.Arrays;
import java.util.Scanner;



public class MaxProfitCoins {
    public  static class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public static TreeNode buildBST(int []arr, int start , int end){
        if(start>end) return null;
        int mid = (start+ end)/2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = buildBST(arr,start, mid-1);
        node.right = buildBST(arr, mid+1, end);
        return node;
    }

    public static int[] findMinMax(TreeNode root){
        int min = root.val, max = root.val;
        if(root.left != null) {
            int[] left = findMinMax(root.left);
            min = Math.min(min, left[0]);
            max = Math.max(max, left[1]);
        }
        if(root.right != null) {
            int[] right = findMinMax(root.right);
            min = Math.min(min, right[0]);
            max = Math.max(max, right[1]);
        }
        return new int[]{min, max};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i]= sc.nextInt();
        }

        Arrays.sort(coins);
        TreeNode root = buildBST(coins, 0,N-1);

        int []minMax = findMinMax(root);
        int maxProfit = Math.abs(minMax[1]-minMax[0]);
        System.out.println(maxProfit);
    }
}
