package com.app.thread;

import com.app.thread.file.FileService;
import com.app.thread.runnables.UserProcessor;
import com.app.thread.user.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class ThreadApplication {

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(ThreadApplication.class, args);

		/*
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
		 */

	}

	@PostConstruct
	public void init() {
		var executorService = Executors.newSingleThreadExecutor();
		var userContent = this.fileService.getFileContent("/new_users.txt");


		for(String line : userContent) {
			Future<Integer> response = executorService.submit(new UserProcessor(line, this.userService));
			try {
				System.out.println("Result of the operation: " + response.get());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		//executorService.shutdown();
		System.out.println("Execution over !!");
	}

}
