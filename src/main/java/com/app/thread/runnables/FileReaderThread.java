package com.app.thread.runnables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReaderThread extends Thread {
    public FileReaderThread() {
        System.out.println("Instancing thread " + Thread.currentThread().threadId());
    }

    private BufferedReader getFileReader() throws IOException {
        var inputStream = getClass().getResourceAsStream("/sample.txt");

        if(inputStream == null) throw new IOException("File sample.txt not found");

        return new BufferedReader(new InputStreamReader(inputStream));
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
