package com.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.trees.TreeNodes.BinarySearchTreeNode;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(bst.isEmpty(), "Tree should be empty initially.");
        bst.insert(10);
        assertFalse(bst.isEmpty(), "Tree should not be empty after inserting an element.");
    }

    @Test
    public void testGetRoot() {
        assertNull(bst.getRoot(), "Root should be null initially.");
        bst.insert(10);
        assertNotNull(bst.getRoot(), "Root should not be null after inserting an element.");
        assertEquals(bst.getRoot().getValue(), 10, "Root value should be 10.");
    }

    @Test
    public void testInsertAndContains() {
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);

        assertTrue(bst.contains(10), "Tree should contain 10.");
        assertTrue(bst.contains(20), "Tree should contain 20.");
        assertTrue(bst.contains(5), "Tree should contain 5.");
        assertFalse(bst.contains(15), "Tree should not contain 15.");
    }

    @Test
    public void testFindMinAndMax() {
        bst.insert(15);
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);
        bst.insert(25);

        assertEquals(5, bst.findMin(), "Minimum value should be 5.");
        assertEquals(25, bst.findMax(), "Maximum value should be 25.");
    }

    @Test
    public void testRemoveLeafNode() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(20);
        bst.insert(14);

 
        assertTrue(bst.contains(14), "Tree should contain 14 before removal.");
        bst.remove(14);
        assertFalse(bst.contains(14), "Tree should not contain 14 after removal.");

        assertTrue(bst.contains(20), "Tree should contain 20 before removal.");
        bst.remove(20);
        assertFalse(bst.contains(20), "Tree should not contain 20 after removal.");

        assertTrue(bst.contains(15), "Tree should contain 15 before removal.");
        bst.remove(15);
        assertFalse(bst.contains(15), "Tree should not contain 15 after removal.");
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);

        assertTrue(bst.contains(15), "Tree should contain 15 before removal.");
        bst.remove(15);
        assertFalse(bst.contains(15), "Tree should not contain 15 after removal.");
        assertTrue(bst.contains(12), "Tree should still contain 12 after removing 15.");
        assertTrue(bst.contains(10), "Tree should still contain 12 after removing 15.");
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);
        bst.insert(20);

        assertTrue(bst.contains(15), "Tree should contain 15 before removal.");
        bst.remove(15);
        assertFalse(bst.contains(15), "Tree should not contain 15 after removal.");
        assertTrue(bst.contains(12), "Tree should still contain 12 after removing 15.");
        assertTrue(bst.contains(20), "Tree should still contain 20 after removing 15.");
    }

    @Test
    public void testMakeEmpty() {
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);

        assertFalse(bst.isEmpty(), "Tree should not be empty.");
        bst.makeEmpty();
        assertTrue(bst.isEmpty(), "Tree should be empty after makeEmpty.");
    }

    @Test
    public void testHeight() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(20);
        bst.insert(15);
        bst.insert(25);

        int height = bst.height(); 
        assertEquals(2, height, "Tree height should be 2.");
    }

    @Test
    public void testDuplicateInsertions() {
        bst.insert(10);
        bst.insert(20);
        assertEquals(1, bst.height(), "Tree height should remain unchanged with duplicate insertion.");
        bst.insert(10); // Duplicate insertion

        assertTrue(bst.contains(10), "Tree should contain 10.");
        assertTrue(bst.contains(20), "Tree should contain 20.");
        assertEquals(1, bst.height(), "Tree height should remain unchanged with duplicate insertion.");
    }

    @Test
    public void testEdgeCaseEmptyTreeOperations() {
        assertThrows(Error.class, bst::findMin, "Finding minimum on an empty tree should throw an error.");
        assertThrows(Error.class, bst::findMax, "Finding maximum on an empty tree should throw an error.");
        assertFalse(bst.contains(10), "Empty tree should not contain any value.");
    }

    public void setUpTraversal() {
        bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);
        bst.insert(12);
        bst.insert(18);
    }

    @Test
    public void testLevelOrderTraverse() {
        setUpTraversal();
        Iterator<BinarySearchTreeNode<Integer>> iterator = bst.levelOrderTraverse().iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(10), iterator.next().getValue());
        assertEquals(Integer.valueOf(5), iterator.next().getValue());
        assertEquals(Integer.valueOf(15), iterator.next().getValue());
        assertEquals(Integer.valueOf(3), iterator.next().getValue());
        assertEquals(Integer.valueOf(7), iterator.next().getValue());
        assertEquals(Integer.valueOf(12), iterator.next().getValue());
        assertEquals(Integer.valueOf(18), iterator.next().getValue());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testPreOrderTraverse() {
        setUpTraversal();
        Iterator<BinarySearchTreeNode<Integer>> iterator = bst.preOrderTraverse().iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(10), iterator.next().getValue());
        assertEquals(Integer.valueOf(5), iterator.next().getValue());
        assertEquals(Integer.valueOf(3), iterator.next().getValue());
        assertEquals(Integer.valueOf(7), iterator.next().getValue());
        assertEquals(Integer.valueOf(15), iterator.next().getValue());
        assertEquals(Integer.valueOf(12), iterator.next().getValue());
        assertEquals(Integer.valueOf(18), iterator.next().getValue());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testPostOrderTraverse() {
        setUpTraversal();
        Iterator<BinarySearchTreeNode<Integer>> iterator = bst.postOrderTraverse().iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next().getValue());
        assertEquals(Integer.valueOf(7), iterator.next().getValue());
        assertEquals(Integer.valueOf(5), iterator.next().getValue());
        assertEquals(Integer.valueOf(12), iterator.next().getValue());
        assertEquals(Integer.valueOf(18), iterator.next().getValue());
        assertEquals(Integer.valueOf(15), iterator.next().getValue());
        assertEquals(Integer.valueOf(10), iterator.next().getValue());
        assertFalse(iterator.hasNext());
    }
}

