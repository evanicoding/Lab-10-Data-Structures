import java.util.ArrayList;
import java.util.List;

/**
 * A simple binary search tree that stores integers.
 * Duplicate values are ignored.
 */
public class BinarySearchTree {

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    /** Inserts a single number into the tree. Returns true if it was added, false if it was a duplicate. */
    public boolean insert(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }

        Node current = root;
        while (true) {
            if (value == current.value) {
                return false; // duplicate
            } else if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    /** Inserts several numbers at once, in the order given. */
    public void insertAll(int... values) {
        for (int v : values) {
            insert(v);
        }
    }

    /** Returns true if the value is in the tree. */
    public boolean contains(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = (value < current.value) ? current.left : current.right;
        }
        return false;
    }

    /** Number of values stored in the tree. */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Height of the tree (-1 if empty, 0 if only a root). */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Returns the values in sorted (ascending) order. */
    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<Integer> out) {
        if (node == null) return;
        inOrder(node.left, out);
        out.add(node.value);
        inOrder(node.right, out);
    }

    /** Returns the values in pre-order (root, left, right). */
    public List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node node, List<Integer> out) {
        if (node == null) return;
        out.add(node.value);
        preOrder(node.left, out);
        preOrder(node.right, out);
    }

    /** Returns the values in post-order (left, right, root). */
    public List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(Node node, List<Integer> out) {
        if (node == null) return;
        postOrder(node.left, out);
        postOrder(node.right, out);
        out.add(node.value);
    }

    /** Smallest value in the tree. Throws if the tree is empty. */
    public int min() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        Node current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    /** Largest value in the tree. Throws if the tree is empty. */
    public int max() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        Node current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    @Override
    public String toString() {
        return inOrder().toString();
    }

    // Demo
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insertAll(50, 30, 70, 20, 40, 60, 80, 30); // second 30 is a duplicate

        System.out.println("In-order:   " + tree.inOrder());
        System.out.println("Pre-order:  " + tree.preOrder());
        System.out.println("Post-order: " + tree.postOrder());
        System.out.println("Size:       " + tree.size());
        System.out.println("Height:     " + tree.height());
        System.out.println("Min / Max:  " + tree.min() + " / " + tree.max());
        System.out.println("Contains 60? " + tree.contains(60));
        System.out.println("Contains 99? " + tree.contains(99));
    }
}
