package com.trees.TreeNodes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class BinarySearchTreeNodeTest {

    @Test
    public void testSetLeftChild() {
        BinarySearchTreeNode<Integer> parent = new BinarySearchTreeNode<>(10);
        BinarySearchTreeNode<Integer> leftChild = new BinarySearchTreeNode<>(5);
        
        parent.setLeftChild(leftChild);
        
        assertEquals(leftChild, parent.getLeftChild());
    }

    @Test
    public void testSetRightChild() {
        BinarySearchTreeNode<Integer> parent = new BinarySearchTreeNode<>(10);
        BinarySearchTreeNode<Integer> rightChild = new BinarySearchTreeNode<>(15);
        
        parent.setRightChild(rightChild);
        
        assertEquals(rightChild, parent.getRightChild());
    }

    @Test
    public void testSetLeftChildException() {
        BinarySearchTreeNode<Integer> parent = new BinarySearchTreeNode<>(10);
        BinarySearchTreeNode<Integer> invalidLeftChild = new BinarySearchTreeNode<>(15);
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parent.setLeftChild(invalidLeftChild);
        });
        
        assertEquals("Left child must be less than parent", exception.getMessage());
    }

    @Test
    public void testSetRightChildException() {
        BinarySearchTreeNode<Integer> parent = new BinarySearchTreeNode<>(10);
        BinarySearchTreeNode<Integer> invalidRightChild = new BinarySearchTreeNode<>(5);
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            parent.setRightChild(invalidRightChild);
        });
        
        assertEquals("Right child must be greater than parent", exception.getMessage());
    }

    @Test
    public void testGetChildren() {
        BinarySearchTreeNode<Integer> parent = new BinarySearchTreeNode<>(10);
        BinarySearchTreeNode<Integer> leftChild = new BinarySearchTreeNode<>(5);
        BinarySearchTreeNode<Integer> rightChild = new BinarySearchTreeNode<>(15);
        
        parent.setLeftChild(leftChild);
        parent.setRightChild(rightChild);
        
        assertEquals(2, parent.getChildren().size());
        assertTrue(parent.getChildren().contains(leftChild));
        assertTrue(parent.getChildren().contains(rightChild));
    }

    @Test
    public void testIsLeaf() {
        BinarySearchTreeNode<Integer> leafNode = new BinarySearchTreeNode<>(10);
        assertTrue(leafNode.isLeaf());
        
        BinarySearchTreeNode<Integer> parent = new BinarySearchTreeNode<>(10);
        BinarySearchTreeNode<Integer> child = new BinarySearchTreeNode<>(5);
        parent.setLeftChild(child);
        
        assertFalse(parent.isLeaf());
    }
}