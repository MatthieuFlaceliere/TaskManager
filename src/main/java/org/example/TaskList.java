package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    // Constructeurs
    public TaskList() {}
    public TaskList(List<Task> tasks) {
        taskList = new ArrayList<>(tasks);
    }

    // Méthodes
    public Task createTask(String description) {
        Task task = new Task(description);
        taskList.add(task);
        return task;
    }

    public void deleteTask(long id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                taskList.remove(task);
                break;
            }
        }
    }

    public void doneTask(long id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setDone();
                break;
            }
        }
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public List<Task> getTasks(boolean done) {
        List<Task> tasksToReturn = new ArrayList<>();
        for (Task task : taskList) {
            if (task.isDone() == !done || task.isDone()) {
                tasksToReturn.add(task);
            }
        }
        return tasksToReturn;
    }
}
