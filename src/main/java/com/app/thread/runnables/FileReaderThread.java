package com.app.thread.runnables;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderThread extends Thread {
    public FileReaderThread() {
        System.out.println("Instancing thread " + Thread.currentThread().threadId());
    }

    private BufferedReader getFileReader() throws IOException {
        var resource = new ClassPathResource("sample.txt");
        var file = resource.getFile();
        var fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

    @Override
    public void run() {
        try(var fileReader = this.getFileReader()) {
            String line = "";

            while((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}
