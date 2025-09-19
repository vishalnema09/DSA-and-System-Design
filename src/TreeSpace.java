import com.sun.source.tree.Tree;

import java.util.*;

class TreeSpace{
    class Node{
        String name;
        Node parent;
        List<Node> children = new ArrayList<>();
        boolean isLocked = false;
        int lockedBy = -1;
        int lockedDescendantCount = 0;

        Node(String name){
            this.name = name;
        }
    }
    Map<String, Node> map = new HashMap<>();

    public TreeSpace(List<String> nodes, int M){
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(nodes.get(0));
        map.put(nodes.get(0), root);
        q.add(root);

        int idx = 1;
        while(!q.isEmpty() && idx < nodes.size()){
            Node curr = q.poll();
            for(int i = 0;i<M && idx < nodes.size();i++){
                Node child = new Node(nodes.get(idx));
                child.parent =curr;
                curr.children.add(child);
                map.put(nodes.get(idx),child);
                q.add(child);
                idx++;
            }
        }
    }

    public boolean lock(String name, int uid){
        Node node = map.get(name);
        if(node.isLocked || node.lockedDescendantCount>0) return false;

        Node temp = node.parent;
        while(temp!=null){
            if(temp.isLocked)return false;
            temp = temp.parent;
        }

        node.isLocked = true;
        node.lockedBy = uid;

        temp = node.parent;
        while(temp!=null){
            temp.lockedDescendantCount++;
            temp = temp.parent;
        }
        return true;
    }


    public boolean unlock(String name, int uid){
        Node node = map.get(name);

        if(!node.isLocked || node.lockedBy != uid)return false;

        node.isLocked = false;
        node.lockedBy=-1;

        Node temp = node.parent;
        while (temp!=null){
            temp.lockedDescendantCount--;
            temp= temp.parent;
        }
        return true;
    }

    public boolean upgrade(String name , int uid){
        Node node = map.get(name);
        if(node.isLocked || node.lockedDescendantCount ==0)return false;
        List<Node> lockedNodes = new ArrayList<>();
        if(!collectLockedNodes(node, lockedNodes,uid)) return false;

        for(Node ln :lockedNodes){
            unlock(ln.name, uid);
        }
        return lock(name, uid);
    }

    private boolean collectLockedNodes(Node node, List<Node> lockedNodes, int uid){
        if(node.isLocked) {
            if (node.lockedBy != uid) return false;
            lockedNodes.add(node);
        }
        for(Node child: node.children){
            if(!collectLockedNodes(child, lockedNodes, uid)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> nodes1 = Arrays.asList("World", "Asia", "Africa", "China", "India", "SouthAfrica", "Egypt");
        TreeSpace tree1 = new TreeSpace(nodes1, 2);

        // Example 1
        System.out.println(tree1.lock("China", 9));   // true
        System.out.println(tree1.lock("India", 9));   // true
        System.out.println(tree1.upgrade("Asia", 9)); // true
        System.out.println(tree1.unlock("India", 9)); // false
        System.out.println(tree1.unlock("Asia", 9));  // true

        // Example 2
        List<String> nodes2 = Arrays.asList("World", "China", "India");
        TreeSpace tree2 = new TreeSpace(nodes2, 2);

        System.out.println(tree2.upgrade("India", 1)); // false
        System.out.println(tree2.lock("World", 9));    // true
    }
}