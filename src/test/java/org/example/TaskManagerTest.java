package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TaskManagerTest {

    private final List<Task> tasksStub = new ArrayList<>() {{
        add(new Task("Test 1", false));
        add(new Task("Test 2", true));
    }};

    @Test
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
        verify(consoleManagerMock, times(2)).WriteLine("""
                        Bienvenue dans votre gestionnaire de tâches !
                        Que souhaitez-vous faire ?
                        1. Lister les tâches à faire
                        2. Ajouter une tâche
                        3. Supprimer une tâche
                        4. Marquer une tâche comme faite
                        5. Quitter
                        """);
        verify(consoleManagerMock, times(1)).WriteLine("Voici la liste de vos tâches :");
        verify(consoleManagerMock, times(1)).WriteLine("1 - Test 1");
    }

    @Test
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
        verify(consoleManagerMock, times(2)).WriteLine("""
                        Bienvenue dans votre gestionnaire de tâches !
                        Que souhaitez-vous faire ?
                        1. Lister les tâches à faire
                        2. Ajouter une tâche
                        3. Supprimer une tâche
                        4. Marquer une tâche comme faite
                        5. Quitter
                        """);
        verify(consoleManagerMock, times(1)).WriteLine("Quelle tâche souhaitez-vous ajouter ?");
        verify(consoleManagerMock, times(1)).WriteLine("Tâche ajoutée !");
    }
}