package com.denis.producerconsumer.model;

public class Task {
    private final int taskId;
    private final String description;

    public Task(int taskId, String description) {
        this.taskId = taskId;
        this.description = description;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                '}';
    }
}
