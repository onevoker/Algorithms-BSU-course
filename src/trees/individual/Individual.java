package trees.individual;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Individual implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Individual(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        BinarySearchTree tree = new BinarySearchTree();
        List<Integer> list = FileWorkerUtil.readFromFile();

        for (int value : list) {
            tree.add(value);
        }

        tree.deleteMaxSemiPathNode();
        tree.preorderTraversal();
    }
}

class BinarySearchTree {
    private Node root;
    private final List<Integer> list = new ArrayList<>();
    private final MaxSemiPathHelper semiPathHelper = new MaxSemiPathHelper();


    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        } else if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public void add(int value) {
        this.root = addRecursive(this.root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        } else if (value > current.value) {
            current.right = deleteRecursive(current.right, value);
            return current;
        } else if (current.right == null && current.left == null) {
            return null;
        } else if (current.left == null) {
            return current.right;
        } else if (current.right == null) {
            return current.left;
        }

        current.value = minValue(current.right);
        current.right = deleteRecursive(current.right, current.value);

        return current;
    }

    private int minValue(Node node) {
        int minValue = node.value;

        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }

        return minValue;
    }

    public void delete(int value) {
        this.root = deleteRecursive(this.root, value);
    }

    private int findLongestSemiPathRecursive(Node node) {
        if (node == null) {
            return 0;
        }

        int leftLength = findLongestSemiPathRecursive(node.left);
        int rightLength = findLongestSemiPathRecursive(node.right);
        int sum = leftLength + rightLength;

        if (sum >= semiPathHelper.maxLength) {
            semiPathHelper.maxLength = sum;
            semiPathHelper.maxNode = node;
        }

        return Math.max(leftLength, rightLength) + 1;
    }

    public void deleteMaxSemiPathNode() {
        findLongestSemiPathRecursive(root);
        delete(semiPathHelper.maxNode.value);
    }

    private void recursivePreorderTraversal(Node node) {
        if (node != null) {
            list.add(node.value);
            recursivePreorderTraversal(node.left);
            recursivePreorderTraversal(node.right);
        }
    }

    public void preorderTraversal() {
        recursivePreorderTraversal(this.root);
        FileWorkerUtil.writeToFile(this.list);
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class MaxSemiPathHelper {
    Node maxNode;
    int maxLength;

    public MaxSemiPathHelper() {
        this.maxNode = null;
        this.maxLength = 0;
    }
}

class FileWorkerUtil {
    public static List<Integer> readFromFile() {
        List<Integer> res = new ArrayList<>();
        File input = new File("in.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                res.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static void writeToFile(List<Integer> list) {
        File output = new File("out.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            for (int value : list) {
                bufferedWriter.write(String.valueOf(value));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
