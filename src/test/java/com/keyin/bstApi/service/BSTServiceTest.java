package com.keyin.bstApi.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keyin.bstApi.model.BSTRecord;
import com.keyin.bstApi.model.TreeNode;
import com.keyin.bstApi.repository.BSTRepository;

@ExtendWith(MockitoExtension.class)
class BSTServiceTest {

    @Mock
    private BSTRepository repository;

    @InjectMocks
    private BSTService bstService;

    private List<Integer> testNumbers;

    @BeforeEach
    void setUp() {
        // Setup test data
        testNumbers = Arrays.asList(10, 5, 15, 3, 7);
    }

    @Test
    void createTree_ValidInput_CreatesCorrectBST() {
        // Given
        BSTRecord mockRecord = new BSTRecord();
        mockRecord.setId("test-id");
        mockRecord.setInputNumbers(testNumbers);
        when(repository.save(any(BSTRecord.class))).thenAnswer(invocation -> {
            BSTRecord savedRecord = invocation.getArgument(0);
            savedRecord.setId("test-id");
            return savedRecord;
        });

        // When
        BSTRecord result = bstService.createTree(testNumbers);

        // Then
        assertNotNull(result, "Result should not be null");
        assertEquals(testNumbers, result.getInputNumbers(), "Input numbers should match");
        
        // Verify the tree structure
        TreeNode root = result.getRootNode();
        assertNotNull(root, "Root node should not be null");
        assertEquals(10, root.getValue(), "Root should have value 10");
        
        // Verify left subtree
        assertNotNull(root.getLeft(), "Left child should not be null");
        assertEquals(5, root.getLeft().getValue(), "Left child should have value 5");
        assertNotNull(root.getLeft().getLeft(), "Left-left child should not be null");
        assertEquals(3, root.getLeft().getLeft().getValue(), "Left-left child should have value 3");
        assertNotNull(root.getLeft().getRight(), "Left-right child should not be null");
        assertEquals(7, root.getLeft().getRight().getValue(), "Left-right child should have value 7");
        
        // Verify right subtree
        assertNotNull(root.getRight(), "Right child should not be null");
        assertEquals(15, root.getRight().getValue(), "Right child should have value 15");
    }

    @Test
    void createTree_NullInput_ThrowsException() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> bstService.createTree(null),
                    "Should throw IllegalArgumentException for null input");
        assertEquals("Numbers list cannot be null or empty", exception.getMessage());
    }

    @Test
    void createTree_EmptyInput_ThrowsException() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> bstService.createTree(List.of()),
                    "Should throw IllegalArgumentException for empty input");
        assertEquals("Numbers list cannot be null or empty", exception.getMessage());
    }

    @Test
    void getPreviousTrees_ReturnsSortedByDateDesc() {
        // Given
        BSTRecord oldRecord = new BSTRecord();
        oldRecord.setId("old-id");
        oldRecord.setInputNumbers(Arrays.asList(1, 2, 3));
        oldRecord.setCreatedAt(new Date(System.currentTimeMillis() - 86400000)); // 1 day ago

        BSTRecord newRecord = new BSTRecord();
        newRecord.setId("new-id");
        newRecord.setInputNumbers(Arrays.asList(4, 5, 6));
        newRecord.setCreatedAt(new Date()); // now

        when(repository.findAllByOrderByCreatedAtDesc())
            .thenReturn(Arrays.asList(newRecord, oldRecord));

        // When
        List<BSTRecord> result = bstService.getPreviousTrees();

        // Then
        assertEquals(2, result.size(), "Should return 2 records");
        assertEquals("new-id", result.get(0).getId(), "First record should be newest");
        assertEquals("old-id", result.get(1).getId(), "Second record should be oldest");
        assertEquals(Arrays.asList(4, 5, 6), result.get(0).getInputNumbers(), "First record should have correct numbers");
        assertEquals(Arrays.asList(1, 2, 3), result.get(1).getInputNumbers(), "Second record should have correct numbers");
    }
} 