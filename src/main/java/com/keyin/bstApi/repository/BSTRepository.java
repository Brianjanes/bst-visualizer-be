// src/main/java/com/kevin/bstApi/repository/BSTRepository.java
package com.keyin.bstApi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.keyin.bstApi.model.BSTRecord;

/**
 * Repository interface for BSTRecord entities.
 * Provides methods to interact with the MongoDB database.
 */
@Repository
public interface BSTRepository extends MongoRepository<BSTRecord, String> {
    
    /**
     * Retrieves all BST records ordered by creation date in descending order.
     *
     * @return List of BST records, newest first
     */
    List<BSTRecord> findAllByOrderByCreatedAtDesc();
}