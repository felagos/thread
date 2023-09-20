package com.app.thread;

import com.app.thread.runnables.FileReaderThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;

@SpringBootApplication
public class ThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadApplication.class, args);

		var fileThreadA = new FileReaderThread();
		var fileThreadB = new FileReaderThread();

		fileThreadA.start();
		fileThreadB.start();

		Runnable runnable = () -> {
			System.out.println("Running from lamda expression with name " + Thread.currentThread().getName());
		};
		var thread = new Thread(runnable);
		thread.start();

		var executor = Executors.newSingleThreadExecutor();
		executor.execute(runnable);

	}

}
