package com.trees;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trees.TreeNodes.GenericTreeNode;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GenericTreeTest {
    private GenericTree<Integer> tree;
    private GenericTreeNode<Integer> root;
    private GenericTreeNode<Integer> child1;
    private GenericTreeNode<Integer> grandChild1;
    private GenericTreeNode<Integer> grandChild2;
    private GenericTreeNode<Integer> grandChild3;
    private GenericTreeNode<Integer> child2;

    @BeforeEach
    public void setUp() {
        root = new GenericTreeNode<>(1);
        child1 = new GenericTreeNode<>(2);
        grandChild1 = new GenericTreeNode<>(4);
        grandChild2 = new GenericTreeNode<>(2);
        
        child2 = new GenericTreeNode<>(3);
        grandChild3 = new GenericTreeNode<>(6);
        
        tree = new GenericTree<>(root);
    }

    @AfterEach
    public void tearDown() {
        tree = null;
    }

    public void withGrandChildren(){
        tree.addChild(root, child1);
        tree.addChild(root, child2);
        tree.addChild(child1, grandChild1);
        tree.addChild(child1, grandChild2);
        tree.addChild(child2, grandChild3);
    }

    @Test
    public void testGetRoot() {
        assertEquals(root, tree.getRoot());
    }

    @Test
    public void testSize() {
        assertEquals(1, tree.size());
        tree.addChild(root, child1);
        assertEquals(2, tree.size());
        tree.addChild(root, child2);
        assertEquals(3, tree.size());
    }

    @Test
    public void testAddChild() {
        tree.addChild(root, child1);
        assertEquals(2, tree.size());
        assertEquals(child1, root.getChildren().get(0));

        tree.addChild(child1, grandChild1);
        assertEquals(3, tree.size());
        assertEquals(grandChild1, child1.getChildren().get(0));
    }

    @Test
    public void testLevelOrderTraverse() {
        tree.addChild(root, child1);
        tree.addChild(root, child2);
        tree.addChild(child1, grandChild1);
        tree.addChild(child1, grandChild2);
        tree.addChild(child2, grandChild3);

        Iterator<GenericTreeNode<Integer>> iterator = tree.levelOrderTraverse().iterator();
        assertEquals(root, iterator.next());
        assertEquals(child1, iterator.next());
        assertEquals(child2, iterator.next());
        assertEquals(grandChild1, iterator.next());
        assertEquals(grandChild2, iterator.next());
        assertEquals(grandChild3, iterator.next());
    }
}