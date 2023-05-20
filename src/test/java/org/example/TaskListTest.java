package org.example;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskListTest {

    private final List<Task> tasksStub = new ArrayList<>() {{
        add(new Task("Test 1", false));
        add(new Task("Test 2", true));
    }};

    @Test
    @Order(1)
    void getTasks() {
        // Given
        TaskList target = new TaskList(tasksStub);
        // When
        List<Task> result = target.getTasks();
        // Then
        assertNotNull(result);
        assertEquals(tasksStub.size(), result.size());
        assertEquals(tasksStub.get(0), result.get(0));
    }

    @Test
    @Order(2)
    void createTask() {
        // Given
        TaskList target = new TaskList(tasksStub);
        String expectedDesc = "Test";
        // When
        Task result = target.createTask(expectedDesc);
        List<Task> resultTasks = target.getTasks();
        // Then
        assertNotNull(result);
        assertEquals(expectedDesc, result.getDescription());
        assertEquals(tasksStub.size() + 1, resultTasks.size());
        assertTrue(resultTasks.contains(result));
    }

    @Test
    @Order(3)
    void deleteTask() {
        // Given
        TaskList target = new TaskList(tasksStub);
        Task taskToDelete = tasksStub.get(0);
        // When
        target.deleteTask(taskToDelete.getId());
        List<Task> resultTasks = target.getTasks();
        // Then
        assertEquals(tasksStub.size() - 1, resultTasks.size());
        assertFalse(resultTasks.contains(taskToDelete));
    }

    @Test
    @Order(4)
    void doneTask() {
        // Given
        TaskList target = new TaskList(tasksStub);
        Task taskToDone = tasksStub.get(0);
        // When
        target.doneTask(taskToDone.getId());
        List<Task> resultTasks = target.getTasks();
        // Then
        assertTrue(taskToDone.isDone());
        assertTrue(resultTasks.contains(taskToDone));
    }

    @Test
    @Order(5)
    void getTasksWhitDone() {
        // Given
        TaskList target = new TaskList(tasksStub);
        // When
        List<Task> result = target.getTasks(true);
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(tasksStub.get(0), result.get(0));
    }

    @Test
    @Order(6)
    void getTasksWhitNotDone() {
        // Given
        TaskList target = new TaskList(tasksStub);
        // When
        List<Task> result = target.getTasks(false);
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(tasksStub.get(1), result.get(0));
    }
}