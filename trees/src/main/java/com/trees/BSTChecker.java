package com.trees;
import java.io.IOException;

import com.trees.TreeNodes.GenericTreeNode;

public class BSTChecker {
    public boolean isBinarySearchTree(GenericTree<Integer> tree) {
        GenericTreeNode<Integer> root = tree.getRoot();
        
        if (!isBinaryTree(root)) {
            return false;
        }

        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinaryTree(GenericTreeNode<Integer> node) {
        if (node == null) {
            return true;
        }
        if (node.getChildren().size() > 2) {
            return false; 
        }
        for (GenericTreeNode<Integer> child : node.getChildren()) {
            if (!isBinaryTree(child)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBST(GenericTreeNode<Integer> node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.getValue() < min || node.getValue() > max) {
            return false;
        }

        GenericTreeNode<Integer> leftChild = !node.getChildren().isEmpty() ? node.getChildren().get(0) : null;
        GenericTreeNode<Integer> rightChild = node.getChildren().size() > 1 ? node.getChildren().get(1) : null;

        return isBST(leftChild, min, node.getValue()) && isBST(rightChild, node.getValue(), max);
    }

    public static void main(String[] args) {
        String basePath = "src/main/java/com/trees/";
        String[] testFiles = { basePath + "test1.dot", basePath + "test2.dot", basePath + "test3.dot",
                basePath + "test4.dot" };

        for (String file : testFiles) {
            try {
                GenericTree<Integer> tree = DotFileReader.createTreeFromDotFile(file, Integer::parseInt);
                BSTChecker checker = new BSTChecker();
                boolean isValidBST = checker.isBinarySearchTree(tree);
                System.out.println(file + " is a BST: " + isValidBST);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
