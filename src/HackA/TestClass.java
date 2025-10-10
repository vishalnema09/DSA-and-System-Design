package HackA;

import com.sun.source.tree.Tree;

import javax.swing.tree.TreeNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TestClass {
    static class TreeNode {
        String name;
        TreeNode parent;
        List<TreeNode> children;
        boolean isLocked;
        int lockedBy;
        int lockedDescendantsCount ;

        public TreeNode (String name,TreeNode parent){
            this.name = name;
            this.parent = parent;
            this.children = new ArrayList<>();
            this.isLocked =false;
            this.lockedBy = -1;
            this.lockedDescendantsCount = 0;
        }
    }
    static class LockingTree{
        private final Map<String , TreeNode> nameToNodeMap;

        public LockingTree() {
            this.nameToNodeMap = new HashMap<>();
        }
        public void buildTree(List<String> nodeNames, int m) {
            if (nodeNames == null || nodeNames.isEmpty()) {
                return;
            }
            TreeNode root = new TreeNode(nodeNames.get(0), null);
            nameToNodeMap.put(nodeNames.get(0), root);

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            int currentIndex = 1;
            while (!queue.isEmpty() && currentIndex < nodeNames.size()) {
                TreeNode parentNode = queue.poll();
                for (int i = 0; i < m && currentIndex < nodeNames.size(); i++) {
                    String childName = nodeNames.get(currentIndex);
                    TreeNode childNode = new TreeNode(childName, parentNode);
                    nameToNodeMap.put(childName, childNode);
                    parentNode.children.add(childNode);
                    queue.add(childNode);
                    currentIndex++;
                }
            }
        }
        public boolean lock(String name, int uid) {
            TreeNode node = nameToNodeMap.get(name);
            if (node == null || node.isLocked) {
                return false;
            }
            if (node.lockedDescendantsCount > 0) {
                return false;
            }
            TreeNode temp = node.parent;
            while (temp != null) {
                if (temp.isLocked) {
                    return false;
                }
                temp = temp.parent;
            }
            node.isLocked = true;
            node.lockedBy = uid;
            temp = node.parent;
            while (temp != null) {
                temp.lockedDescendantsCount++;
                temp = temp.parent;
            }
            return true;
        }

        public boolean unlock(String name, int uid) {
            TreeNode node = nameToNodeMap.get(name);
            if (node == null || !node.isLocked || node.lockedBy != uid) {
                return false;
            }
            node.isLocked = false;
            node.lockedBy = -1;
            TreeNode temp = node.parent;
            while (temp != null) {
                temp.lockedDescendantsCount--;
                temp = temp.parent;
            }
            return true;
        }

        public boolean upgradeLock(String name, int uid) {
            TreeNode node = nameToNodeMap.get(name);
            if (node == null || node.isLocked || node.lockedDescendantsCount == 0) {
                return false;
            }
            TreeNode temp = node.parent;
            while (temp != null) {
                if (temp.isLocked) {
                    return false;
                }
                temp = temp.parent;
            }
            List<TreeNode> descendantsToUnlock = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();
                for (TreeNode child : current.children) {
                    if (child.isLocked) {
                        if (child.lockedBy != uid) {
                            return false;
                        }
                        descendantsToUnlock.add(child);
                    }
                    if (child.lockedDescendantsCount > 0) {
                        queue.add(child);
                    }
                }
            }
            if (descendantsToUnlock.isEmpty()) {
                return false;
            }
            for (TreeNode desc : descendantsToUnlock) {
                desc.isLocked = false;
                desc.lockedBy = -1;
                TreeNode ancestor = desc.parent;
                while (ancestor != null) {
                    ancestor.lockedDescendantsCount--;
                    ancestor = ancestor.parent;
                }
            }
            node.isLocked = true;
            node.lockedBy = uid;
            TreeNode ancestor = node.parent;
            while (ancestor != null) {
                ancestor.lockedDescendantsCount++;
                ancestor = ancestor.parent;
            }
            return true;
        }
    }


    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int q = Integer.parseInt(br.readLine());
        List<String> nodeNames = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nodeNames.add(br.readLine());
        }
        LockingTree tree = new LockingTree();
        tree.buildTree(nodeNames, m);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            int opType = Integer.parseInt(query[0]);
            String nodeName = query[1];
            int userId = Integer.parseInt(query[2]);
            boolean result = false;
            switch (opType) {
                case 1:
                    result = tree.lock(nodeName, userId);
                    break;
                case 2:
                    result = tree.unlock(nodeName, userId);
                    break;
                case 3:
                    result = tree.upgradeLock(nodeName, userId);
                    break;
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb.toString());
    }
}
