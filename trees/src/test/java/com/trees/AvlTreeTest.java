package com.trees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.trees.TreeNodes.AvlTreeNode;


public class AvlTreeTest {

    private AvlTree<Integer> avlTree;

    @BeforeEach
    public void setUp() {
        avlTree = new AvlTree<>();
    }

    @Test
    public void testInsert() {
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(25);

        assertFalse(avlTree.isEmpty());
    }

    @Test
    public void testRemove() {
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(25);

        avlTree.remove(40);
        avlTree.remove(50);

        assertFalse(avlTree.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(avlTree.isEmpty());
        avlTree.insert(10);
        assertFalse(avlTree.isEmpty());
    }

    @Test
    public void testBalanceAfterInsert() {
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);

        AvlTreeNode<Integer> root = avlTree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(10, root.getLeftChild().getValue());
        assertEquals(30, root.getRightChild().getValue());

        // For double rotation
        avlTree.insert(25);
        avlTree.insert(28);

        root = avlTree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(28, root.getRightChild().getValue());
        assertEquals(28, root.getRightChild().getValue());
        assertEquals(25, root.getRightChild().getLeftChild().getValue());
        assertEquals(30, root.getRightChild().getRightChild().getValue());

        assertEquals(2, root.getHeight());
        assertEquals(0, root.getLeftChild().getHeight());
        assertEquals(1, root.getRightChild().getHeight());
    }

    @Test
    public void testBalanceAfterRemove() {
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(25);

        avlTree.remove(40);
        avlTree.remove(50);
        AvlTreeNode<Integer> root = avlTree.getRoot();

        assertEquals(20, root.getValue());
        assertEquals(2, root.getHeight());
        assertEquals(10, root.getLeftChild().getValue());
        assertEquals(0, root.getLeftChild().getHeight());
        assertEquals(1, root.getRightChild().getHeight());
        assertEquals(30, root.getRightChild().getValue());
        assertEquals(25, root.getRightChild().getLeftChild().getValue());
    }
}