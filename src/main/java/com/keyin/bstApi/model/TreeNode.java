// src/main/java/com/kevin/bstApi/model/TreeNode.java
package com.keyin.bstApi.model;

import java.util.Objects;

/**
 * Represents a node in a Binary Search Tree.
 * Each node contains a value and references to its left and right children.
 */
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    // Default constructor needed for MongoDB
    public TreeNode() {
    }

    /**
     * Creates a new TreeNode with the specified value.
     *
     * @param value The integer value to store in this node
     */
    public TreeNode(int value) {
        this.value = value;
    }

    // Getters and setters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return value == treeNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }
}