package com.app.thread;

import com.app.thread.runnables.FileReaderThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadApplication.class, args);

		var fileThreadA = new FileReaderThread();
		var fileThreadB = new FileReaderThread();
		var fileThreadC = new FileReaderThread();

		//fileThreadA.start();
		//fileThreadB.start();
		//fileThreadC.start();

		Runnable runnable = () -> {
			System.out.println("Running from lamda expression");
		};
		var thread = new Thread(runnable);
		thread.start();

	}

}
