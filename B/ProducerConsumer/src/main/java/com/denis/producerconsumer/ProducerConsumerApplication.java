package com.denis.producerconsumer;

import com.denis.producerconsumer.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@SpringBootApplication
public class ProducerConsumerApplication {

    public static void main(String[] args) {
        BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<>(10);

        Thread producer1 = new Thread(new Producer(taskQueue), "Producer-1");
        Thread producer2 = new Thread(new Producer(taskQueue), "Producer-2");

        Thread consumer1 = new Thread(new Consumer(taskQueue), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(taskQueue), "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            producer1.interrupt();
            producer2.interrupt();
            consumer1.interrupt();
            consumer2.interrupt();
        }));
    }
}
