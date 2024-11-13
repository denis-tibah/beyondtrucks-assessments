package com.denis.producerconsumer;

import com.denis.producerconsumer.model.Task;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Task> taskQueue;

    public Consumer(BlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = taskQueue.take();
                System.out.println("Consumed: " + task);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Consumer interrupted");
        }
    }
}
