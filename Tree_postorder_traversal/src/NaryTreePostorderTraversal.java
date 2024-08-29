import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}

public class NaryTreePostorderTraversal {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        // Check if node.children is null before iterating
        if (node.children != null) {
            for (Node child : node.children) {
                traverse(child, result);
            }
        }
        result.add(node.val);
    }

    public static void main(String[] args) {
        // Example 1
        Node root1 = new Node(1, List.of(
                new Node(3, List.of(new Node(5), new Node(6))),
                new Node(2),
                new Node(4)
        ));
        NaryTreePostorderTraversal traversal = new NaryTreePostorderTraversal();
        System.out.println(traversal.postorder(root1)); // Output: [5, 6, 3, 2, 4, 1]

        Node root2 = new Node(1, List.of(
                new Node(2),
                new Node(3, List.of(
                        new Node(6),
                        new Node(7, List.of(new Node(14)))
                )),
                new Node(4, List.of(
                        new Node(12),
                        new Node(8, List.of(new Node(9), new Node(10)))
                )),
                new Node(5, List.of(new Node(13)))
        ));
        System.out.println(traversal.postorder(root2));
    }
}
