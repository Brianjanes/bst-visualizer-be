// src/main/java/com/kevin/bstApi/model/BSTRecord.java
package com.keyin.bstApi.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a Binary Search Tree record in the database.
 * Contains the input numbers, the constructed tree, and metadata.
 */
@Document(collection = "bst_records")
public class BSTRecord {
    @Id
    private String id;
    private List<Integer> inputNumbers;
    private TreeNode rootNode;
    private Date createdAt;

    public BSTRecord() {
        this.createdAt = new Date();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getInputNumbers() {
        return inputNumbers;
    }

    public void setInputNumbers(List<Integer> inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BSTRecord bstRecord = (BSTRecord) o;
        return Objects.equals(id, bstRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BSTRecord{" +
                "id='" + id + '\'' +
                ", inputNumbers=" + inputNumbers +
                ", createdAt=" + createdAt +
                '}';
    }
}