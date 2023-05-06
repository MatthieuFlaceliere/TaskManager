package org.example;

public class Main {
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager();
        TaskList taskList = new TaskList();
        TaskManager taskManager = new TaskManager(consoleManager, taskList);
        taskManager.run();
    }
}