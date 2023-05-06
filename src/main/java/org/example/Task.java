package org.example;

public class Task {

    // Variables d'instance
    private final Long id;
    private String description;
    private boolean done;
    private static Long counter = 0L;

    // Constructeurs
    public Task() {
        id = ++counter;
    }
    public Task(String description) {
        this();
        this.description = description;
    }

    public Task(String description, boolean done) {
        this(description);
        this.done = done;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }
    public void setDone() {
        this.done = true;
    }

    // Méthodes
    @Override
    public String toString() {
        return id + " - " + description + (done ? " (fait)" : "");
    }
}
