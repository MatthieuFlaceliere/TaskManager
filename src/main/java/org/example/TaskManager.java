package org.example;

public class TaskManager {

    private final IConsoleManager consoleManager;
    private final TaskList taskList;

    public TaskManager(IConsoleManager consoleManager, TaskList taskList) {
        this.consoleManager = consoleManager;
        this.taskList = taskList;
    }

    public void run(){
        Long userInput = 0L;
        while (userInput != 5) {
            consoleManager.WriteLine("""
                        Bienvenue dans votre gestionnaire de tâches !
                        Que souhaitez-vous faire ?
                        1. Lister les tâches à faire
                        2. Ajouter une tâche
                        3. Supprimer une tâche
                        4. Marquer une tâche comme faite
                        5. Quitter
                        """);

            userInput = consoleManager.ReadLong();
            consoleManager.Clear();
            switch (Integer.parseInt(userInput.toString())) {
                case 1 -> {
                    consoleManager.WriteLine("Voici la liste de vos tâches :");
                    taskList.getTasks().forEach(task -> consoleManager.WriteLine(task.toString()));
                }
                case 2 -> {
                    consoleManager.WriteLine("Quelle tâche souhaitez-vous ajouter ?");
                    String taskDescription = consoleManager.ReadLine();
                    taskList.createTask(taskDescription);
                    consoleManager.WriteLine("Tâche ajoutée !");
                }
                case 3 -> {
                    taskList.getTasks().forEach(task -> consoleManager.WriteLine(task.toString()));
                    consoleManager.WriteLine("Quelle tâche souhaitez-vous supprimer ?");
                    Long taskToDelete = consoleManager.ReadLong();
                    taskList.deleteTask(taskToDelete);
                    consoleManager.WriteLine("Tâche supprimée !");
                }
                case 4 -> {
                    taskList.getTasks().forEach(task -> consoleManager.WriteLine(task.toString()));
                    consoleManager.WriteLine("Quelle tâche souhaitez-vous marquer comme faite ?");
                    Long taskToMarkAsDone = consoleManager.ReadLong();
                    taskList.doneTask(taskToMarkAsDone);
                    consoleManager.WriteLine("Tâche marquée comme faite !");
                }
                default -> consoleManager.WriteLine("Je n'ai pas compris votre choix.");
            }
            consoleManager.Clear();
        }
    }

}
