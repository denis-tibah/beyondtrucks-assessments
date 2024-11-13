package com.denis.producerconsumer;

import com.denis.producerconsumer.model.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private final BlockingQueue<Task> taskQueue;
    private static final AtomicInteger taskIdGenerator = new AtomicInteger(1);

    public Producer(BlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int taskId = taskIdGenerator.getAndIncrement();
                Task task = new Task(taskId, "Task Description " + taskId);
                taskQueue.put(task); // Ensures blocking if the queue is full
                System.out.println("Produced: " + task);
                Thread.sleep(500); // Simulate task production delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted");
        }
    }
}
