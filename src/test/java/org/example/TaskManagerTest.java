package org.example;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskManagerTest {

    private final static List<Task> tasksStub = new ArrayList<>() {{
        add(new Task("Test 1", false));
        add(new Task("Test 2", true));
    }};
    private final String MENU = """
                        Bienvenue dans votre gestionnaire de tâches !
                        Que souhaitez-vous faire ?
                        1. Lister les tâches à faire
                        2. Ajouter une tâche
                        3. Supprimer une tâche
                        4. Marquer une tâche comme faite
                        5. Quitter
                        """;
    private final String LIST_TASKS = "Voici la liste de vos tâches :";
    private final String ADD_TASK = "Quelle tâche souhaitez-vous ajouter ?";
    private final String TASK_ADDED = "Tâche ajoutée !";
    private final String DELETE_TASK = "Quelle tâche souhaitez-vous supprimer ?";
    private final String TASK_DELETED = "Tâche supprimée !";
    private final String MARK_AS_DONE = "Quelle tâche souhaitez-vous marquer comme faite ?";
    private final String TASK_MARKED_AS_DONE = "Tâche marquée comme faite !";
    private final String UNKNOWN_CHOICE = "Je n'ai pas compris votre choix.";

    @Test
    @Order(1)
    void runListTask(){
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(1L)
                .thenReturn(5L);

        TaskList taskListStub = new TaskList(tasksStub);

        TaskManager target = new TaskManager(consoleManagerMock, taskListStub);
        // When
        target.run();
        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(6)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();
        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(MENU, capturedArguments.get(4));
        assertEquals(LIST_TASKS, capturedArguments.get(1));
        assertEquals(tasksStub.get(0).toString(), capturedArguments.get(2));
        assertEquals(tasksStub.get(1).toString(), capturedArguments.get(3));
        assertEquals(UNKNOWN_CHOICE, capturedArguments.get(5));
    }

    @Test
    @Order(2)
    void runAddTask(){
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(2L)
                .thenReturn(5L);
        when(consoleManagerMock.ReadLine())
                .thenReturn("Test 3");

        TaskList taskListStub = new TaskList(tasksStub);

        TaskManager target = new TaskManager(consoleManagerMock, taskListStub);
        // When
        target.run();
        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(5)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();
        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(ADD_TASK, capturedArguments.get(1));
        assertEquals(TASK_ADDED, capturedArguments.get(2));
        assertEquals(MENU, capturedArguments.get(3));
        assertEquals(UNKNOWN_CHOICE, capturedArguments.get(4));
    }

    @Test
    @Order(3)
    void runDeleteTask(){
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(3L)
                .thenReturn(1L)
                .thenReturn(5L);

        TaskList taskListStub = new TaskList(tasksStub);

        TaskManager target = new TaskManager(consoleManagerMock, taskListStub);
        // When
        target.run();
        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(7)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();
        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(tasksStub.get(0).toString(), capturedArguments.get(1));
        assertEquals(tasksStub.get(1).toString(), capturedArguments.get(2));
        assertEquals(DELETE_TASK, capturedArguments.get(3));
        assertEquals(TASK_DELETED, capturedArguments.get(4));
        assertEquals(MENU, capturedArguments.get(5));
        assertEquals(UNKNOWN_CHOICE, capturedArguments.get(6));
    }

    @Test
    @Order(4)
    void runMarkTaskAsDone(){
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(4L)
                .thenReturn(1L)
                .thenReturn(5L);
        List<Task> tasksStubForTaskAsDone = new ArrayList<>() {{
            add(new Task("Test do mark as done", false));
        }};
        TaskList taskListStub = new TaskList(tasksStubForTaskAsDone);

        TaskManager target = new TaskManager(consoleManagerMock, taskListStub);
        // When
        target.run();
        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(6)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();
        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(tasksStubForTaskAsDone.get(0).toString(), capturedArguments.get(1));
        assertEquals(MARK_AS_DONE, capturedArguments.get(2));
        assertEquals(TASK_MARKED_AS_DONE, capturedArguments.get(3));
        assertEquals(MENU, capturedArguments.get(4));
        assertEquals(UNKNOWN_CHOICE, capturedArguments.get(5));
    }
}