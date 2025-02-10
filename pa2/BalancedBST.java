package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BalancedBST<E extends Comparable<E>> {

    // Inner class for the nodes of the balanced BST
    private class Node {
        E key;
        Node left, right;
        int height;

        Node(E key) {
            this.key = key;
            height = 1;
        }
    }

    private Node root;
    private Comparator<? super E> comparator;

    // Public constructor without a comparator (uses natural ordering)
    public BalancedBST() {
        this(null);
    }

    // Public constructor with a comparator (custom ordering)
    public BalancedBST(Comparator<? super E> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    // Public method to get the number of elements in the BST
    public int size() {
        return size(root);
    }

    // Helper method to get the size of a node's subtree
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    // Public method for pre-order traversal of the BST
    public List<E> preOrderTraversal() {
        List<E> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    // Helper method for pre-order traversal (recursive)
    private void preOrderTraversal(Node node, List<E> result) {
        if (node != null) {
            result.add(node.key); // Add the current node's key to the result list
            preOrderTraversal(node.left, result); // Recursively traverse the left subtree
            preOrderTraversal(node.right, result); // Recursively traverse the right subtree
        }
    }

    // Public method for in-order traversal of the BST
    public List<E> inOrderTraversal() {
        List<E> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    // Helper method for in-order traversal (recursive)
    private void inOrderTraversal(Node node, List<E> result) {
        if (node != null) {
            inOrderTraversal(node.left, result); // Recursively traverse the left subtree
            result.add(node.key); // Add the current node's key to the result list
            inOrderTraversal(node.right, result); // Recursively traverse the right subtree
        }
    }

    // Public method for post-order traversal of the BST
    public List<E> postOrderTraversal() {
        List<E> result = new ArrayList<>();
        postOrderTraversal(root, result);
        return result;
    }

    // Helper method for post-order traversal (recursive)
    private void postOrderTraversal(Node node, List<E> result) {
        if (node != null) {
            postOrderTraversal(node.left, result); // Recursively traverse the left subtree
            postOrderTraversal(node.right, result); // Recursively traverse the right subtree
            result.add(node.key); // Add the current node's key to the result list
        }
    }

    // Overriding toString() method to get a string representation of the BST
    @Override
    public String toString() {
        List<E> elements = inOrderTraversal();
        return elements.toString();
    }

    // Protected method for left rotation about a specified root
    protected Node leftRotation(Node rotationRoot) {
        Node newRoot = rotationRoot.right;
        rotationRoot.right = newRoot.left;
        newRoot.left = rotationRoot;

        rotationRoot.height = 1 + Math.max(getHeight(rotationRoot.left), getHeight(rotationRoot.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    // Protected method for right rotation about a specified root
    protected Node rightRotation(Node rotationRoot) {
        Node newRoot = rotationRoot.left;
        rotationRoot.left = newRoot.right;
        newRoot.right = rotationRoot;

        rotationRoot.height = 1 + Math.max(getHeight(rotationRoot.left), getHeight(rotationRoot.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));

        return newRoot;
    }

    // Protected method for left-right double rotation about a specified root
    protected Node leftRightRotation(Node rotationRoot) {
        rotationRoot.left = leftRotation(rotationRoot.left);
        return rightRotation(rotationRoot);
    }

    // Protected method for right-left double rotation about a specified root
    protected Node rightLeftRotation(Node rotationRoot) {
        rotationRoot.right = rightRotation(rotationRoot.right);
        return leftRotation(rotationRoot);
    }

    // Public method to add an element to the BST
    public boolean add(E element) {
        if (contains(element)) {
            return false; // Element already present in the BST, return false
        }
        root = insert(root, element);
        return true; // Element added to the BST, return true
    }

    // Private helper method to insert an element into the BST (recursive)
    private Node insert(Node node, E element) {
        if (node == null) {
            return new Node(element); // Reached the correct position for insertion
        }

        int cmp = compare(element, node.key); // Compare the element with the current node's key

        if (cmp < 0) {
            node.left = insert(node.left, element); // Recursively insert into the left subtree
        } else {
            node.right = insert(node.right, element); // Recursively insert into the right subtree
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node); // Get the balance factor of the node

        // Perform rotations if necessary to maintain balance
        if (balance > 1 && compare(element, node.left.key) < 0) {
            return rightRotation(node);
        }

        if (balance < -1 && compare(element, node.right.key) > 0) {
            return leftRotation(node);
        }

        if (balance > 1 && compare(element, node.left.key) > 0) {
            return leftRightRotation(node);
        }

        if (balance < -1 && compare(element, node.right.key) < 0) {
            return rightLeftRotation(node);
        }

        return node;
    }

    // Public method to remove an element from the BST
    public boolean remove(E element) {
        if (!contains(element)) {
            return false; // Element not found in the BST, return false
        }
        root = delete(root, element);
        return true; // Element removed from the BST, return true
    }

    // Private helper method to delete an element from the BST (recursive)
    private Node delete(Node node, E element) {
        if (node == null) {
            return node;
        }

        int cmp = compare(element, node.key);

        if (cmp < 0) {
            node.left = delete(node.left, element); // Recursively delete from the left subtree
        } else if (cmp > 0) {
            node.right = delete(node.right, element); // Recursively delete from the right subtree
        } else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = findMinNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        // Perform rotations if necessary to maintain balance after deletion
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotation(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            return leftRightRotation(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotation(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            return rightLeftRotation(node);
        }

        return node;
    }

    // Private helper method to find the node with the minimum key in a subtree
    private Node findMinNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Public method to check if the BST contains a specific element
    public boolean contains(E element) {
        return search(root, element);
    }

    // Private helper method to search for an element in the BST (recursive)
    private boolean search(Node node, E element) {
        if (node == null) {
            return false; // Element not found in the current subtree
        }

        int cmp = compare(element, node.key);

        if (cmp < 0) {
            return search(node.left, element); // Recursively search in the left subtree
        } else if (cmp > 0) {
            return search(node.right, element); // Recursively search in the right subtree
        } else {
            return true; // Element found at the current node
        }
    }

    // Private helper method to get the height of a node (null-safe)
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    // Private helper method to get the balance factor of a node (null-safe)
    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // Private helper method to compare two elements using the comparator (if available)
    private int compare(E a, E b) {
        if (comparator == null) {
            return a.compareTo(b);
        } else {
            return comparator.compare(a, b);
        }
    }

    public static void main(String[] args) {
        BalancedBST<Integer> bst = new BalancedBST<>();
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(8);

        System.out.println("In-order Traversal: " + bst.inOrderTraversal()); // Output: [2, 3, 4, 5, 6, 7, 8]

        bst.remove(4);
        System.out.println("In-order Traversal after removing 4: " + bst.inOrderTraversal()); // Output: [2, 3, 5, 6, 7, 8]
    
    
    
    
    
    }

    
}
