import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root== null)return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i =0;i<size;i++){
                TreeNode node = q.poll();
                level.add(node.val);

                if(node.left!=null)q.add(node.left);
                if(node.right!=null)q.add(node.right);
            }
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        LevelOrderTraversal lot = new LevelOrderTraversal();

        TreeNode root = lot.new TreeNode(1);
        root.left = lot.new TreeNode(2);
        root.right = lot.new TreeNode(3);
        root.left.left = lot.new TreeNode(4);
        root.left.right = lot.new TreeNode(5);
        root.right.right = lot.new TreeNode(6);

        List<List<Integer>> result = lot.levelOrder(root);
        System.out.println(result);
    }
}
