package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getId() {
        // Given
        Task target = new Task();
        // When
        Long result = target.getId();
        // Then
        assertNotNull(result);
    }

    @Test
    void getDescription() {
        // Given
        String expectedDesc = "Test";
        Task target = new Task(expectedDesc);
        // When
        String result = target.getDescription();
        // Then
        assertEquals(expectedDesc, result);
    }

    @Test
    void setDescription() {
        // Given
        String expectedDesc = "Test";
        Task target = new Task();
        // When
        target.setDescription(expectedDesc);
        String result = target.getDescription();
        // Then
        assertEquals(expectedDesc, result);
    }

    @Test
    void isDone() {
        // Given
        Task target = new Task();
        // When
        boolean result = target.isDone();
        // Then
        assertFalse(result);
    }

    @Test
    void setDone() {
        // Given
        Task target = new Task();
        // When
        target.setDone();
        boolean result = target.isDone();
        // Then
        assertTrue(result);
    }
}