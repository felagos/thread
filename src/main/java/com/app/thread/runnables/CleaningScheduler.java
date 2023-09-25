package com.app.thread.runnables;

import com.app.thread.file.FileService;

public class CleaningScheduler implements Runnable {

    @Override
    public void run() {
        System.out.println("Cleaning process started");
    }
}
