package trees;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionTrees2 implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new SolutionTrees2(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        BinarySearchTree tree = new BinarySearchTree();
        List<Integer> list = readFromFile();

        for (int value : list.subList(1, list.size())) {
            tree.add(value);
        }
        tree.delete(list.get(0));

        tree.preorderTraversal();
    }

    private static List<Integer> readFromFile() {
        List<Integer> res = new ArrayList<>();
        File input = new File("in.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            res.add(Integer.parseInt(bufferedReader.readLine()));
            bufferedReader.readLine();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                res.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}

class BinarySearchTree {
    private Node root;
    private final List<Integer> list = new ArrayList<>();

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

    private void recursivePreorderTraversal(Node node) {
        if (node != null) {
            list.add(node.value);
            recursivePreorderTraversal(node.left);
            recursivePreorderTraversal(node.right);
        }
    }

    public void preorderTraversal() {
        recursivePreorderTraversal(this.root);
        writeToFile();
    }

    private void writeToFile() {
        File output = new File("out.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            for (int value : this.list) {
                bufferedWriter.write(String.valueOf(value));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
