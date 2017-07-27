import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

public class BST {

    @DataProvider
    public Object[][] trees() {
        return new Object[][]{
                {validTree(), true},
                {wrongTopTree(), false},
                {wrongBottomTree(), false},
                {sameValueTree(), false},
                {wrongInnerValueTree(), false}
        };
    }

    @Test(dataProvider = "trees")
    public static void test(Node root, boolean isBST) {
        root.prefix();
        assertEquals(new BST().checkBST(root), isBST);
    }

    boolean checkBST(Node root) {
        List<Integer> prefixedTree = new ArrayList<>();
        prefix(root, prefixedTree);
        for (int i = 1; i < prefixedTree.size(); i++) {
            if (prefixedTree.get(i) <= prefixedTree.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    void prefix(Node node, List<Integer> prefixedTree) {
        if (node.left != null) {
            prefix(node.left, prefixedTree);
        }
        prefixedTree.add(node.data);
        if (node.right != null) {
            prefix(node.right, prefixedTree);
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public void prefix() {
            prefix(this);
        }

        private void prefix(Node node) {
            if (node.left != null) {
                prefix(node.left);
            }
            System.out.print(node.data + " ");
            if (node.right != null) {
                prefix(node.right);
            }
        }
    }

    private static Node validTree() {
        List<Node> nodes = createNodes(7);
        nodes.get(3).left = nodes.get(1);
        nodes.get(3).right = nodes.get(5);

        nodes.get(1).left = nodes.get(0);
        nodes.get(1).right = nodes.get(2);

        nodes.get(5).left = nodes.get(4);
        nodes.get(5).right = nodes.get(6);

        return nodes.get(3);
    }

    private static Node wrongTopTree() {
        Node root = validTree();
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    private static Node wrongBottomTree() {
        Node root = validTree();
        Node bottom = root.left;
        Node tmp = bottom.left;
        bottom.left = bottom.right;
        bottom.right = tmp;
        return root;
    }

    private static Node sameValueTree() {
        Node root = validTree();
        root.right.left.data = 6;
        return root;
    }

    private static Node wrongInnerValueTree() {
        Node root = validTree();
        root.right.left.data = 1;
        return root;
    }

    private static List<Node> createNodes(int no) {
        return IntStream.range(0, no)
                .mapToObj(Node::new)
                .collect(Collectors.toList());
    }
}
