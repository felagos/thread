package com.app.thread.file;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private BufferedReader getFileReader(String path) throws IOException {
        var inputStream = getClass().getResourceAsStream(path);

        if(inputStream == null) throw new IOException("File sample.txt not found");

        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public List<String> getFileContent(String path) {
        var list = new ArrayList<String>();
        try(var fileReader = this.getFileReader(path)) {
            String line = "";

            while((line = fileReader.readLine()) != null) {
                list.add(line);
            }
        }  catch(IOException ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
