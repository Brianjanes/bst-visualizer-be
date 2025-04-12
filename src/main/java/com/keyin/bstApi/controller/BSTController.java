package com.keyin.bstApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyin.bstApi.model.BSTRecord;
import com.keyin.bstApi.service.BSTService;

/**
 * REST controller for Binary Search Tree operations.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BSTController {

    @Autowired
    private BSTService bstService;

    /**
     * Creates a new Binary Search Tree from a list of numbers.
     *
     * @param numbers List of integers to build the tree from
     * @return BSTRecord containing the created BST
     */
    @PostMapping("/process-numbers")
    public BSTRecord processNumbers(@RequestBody List<Integer> numbers) {
        return bstService.createTree(numbers);
    }

    /**
     * Retrieves all previously created trees.
     *
     * @return List of BST records
     */
    @GetMapping("/previous-trees")
    public List<BSTRecord> getPreviousTrees() {
        return bstService.getPreviousTrees();
    }
}