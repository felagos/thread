package com.app.thread.factory;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    public static int counter = 0;

    @Override
    public Thread newThread(Runnable runnable) {
        var thread = new Thread(runnable);
        thread.setName("Custom Thread - " + counter);

        return thread;
    }
}
