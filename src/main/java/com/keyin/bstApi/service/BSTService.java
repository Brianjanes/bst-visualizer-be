package com.keyin.bstApi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keyin.bstApi.model.BSTRecord;
import com.keyin.bstApi.model.TreeNode;
import com.keyin.bstApi.repository.BSTRepository;

@Service
public class BSTService {

    private static final Logger logger = LoggerFactory.getLogger(BSTService.class);
    @Autowired
    private BSTRepository repository;

    /**
     * Creates a new Binary Search Tree from a list of numbers.
     *
     * @param numbers List of integers to build the tree from
     * @return BSTRecord containing the created tree
     */
    @Transactional
    public BSTRecord createTree(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            logger.error("Invalid input: numbers list is null or empty");
            throw new IllegalArgumentException("Numbers list cannot be null or empty");
        }
        
        logger.info("Creating BST from {} numbers", numbers.size());

        TreeNode root = null;

        // Build the tree by inserting each number
        for (int num : numbers) {
            root = insertNode(root, num);
        }

        // Create and save the record
        BSTRecord record = new BSTRecord();
        record.setInputNumbers(numbers);
        record.setRootNode(root);

        BSTRecord savedRecord = repository.save(record);
        logger.info("BST created successfully with ID: {}", savedRecord.getId());
        
        return savedRecord;
    }

    /**
     * Builds a Binary Search Tree from a list of numbers.
     *
     * @param numbers List of integers
     * @return Root node of the created tree
     */
    private TreeNode buildBST(List<Integer> numbers) {
        TreeNode root = null;

        for (int num : numbers) {
            root = insertNode(root, num);
        }

        return root;
    }

    /**
     * Inserts a value into the Binary Search Tree.
     *
     * @param root Current root node
     * @param value Value to insert
     * @return Updated root node
     */
    private TreeNode insertNode(TreeNode root, int value) {
        // Base case: empty tree or leaf node
        if (root == null) {
            return new TreeNode(value);
        }

        // Recursive insertion
        if (value < root.getValue()) {
            root.setLeft(insertNode(root.getLeft(), value));
        } else {
            root.setRight(insertNode(root.getRight(), value));
        }

        return root;
    }

    /**
     * Retrieves all previously created trees, ordered by creation date.
     *
     * @return List of BST records
     */
    public List<BSTRecord> getPreviousTrees() {
        logger.info("Retrieving previous trees");
        return repository.findAllByOrderByCreatedAtDesc();
    }
}