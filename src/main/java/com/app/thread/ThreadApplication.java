package com.app.thread;

import com.app.thread.file.FileService;
import com.app.thread.runnables.CleaningScheduler;
import com.app.thread.runnables.UserProcessor;
import com.app.thread.user.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.*;

@SpringBootApplication
public class ThreadApplication {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class, args);
    }

    @PostConstruct
    public void init() {
        var executorService = Executors.newSingleThreadExecutor();
        var userContent = this.fileService.getFileContent("/new_users.txt");

        for (String line : userContent) {
            Future<Integer> response = executorService.submit(new UserProcessor(line, this.userService));
            try {
                Integer totalSaved = response.get();
                System.out.println("Result of the operation: " + totalSaved);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
        System.out.println("Execution over !!");
    }

    @PostConstruct
    public void initScheduling() throws ExecutionException, InterruptedException {
        var scheduler = new CleaningScheduler();
        var schedulerService = Executors.newSingleThreadScheduledExecutor();

        var response = schedulerService.schedule(scheduler, 5, TimeUnit.SECONDS);
        response.get();

        schedulerService.shutdown();
        //schedulerService.scheduleAtFixedRate(scheduler, 5, 4, TimeUnit.SECONDS);
        //schedulerService.scheduleWithFixedDelay(scheduler, 5, 4, TimeUnit.SECONDS);
    }

    @PostConstruct
    public void initComputableFuture() throws ExecutionException, InterruptedException {
        var future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("CompletableFuture 1");
        });

        var future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("CompletableFuture 2");
        });

        var future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("CompletableFuture 3");
            return "CompletableFuture 3";
        });

        CompletableFuture.allOf(future, future2, future3).get();

        System.out.println("Completable futures completed");

    }

}
