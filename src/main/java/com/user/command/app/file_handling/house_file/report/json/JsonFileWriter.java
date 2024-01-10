package com.user.command.app.file_handling.house_file.report.json;


import com.user.command.app.file_handling.house_file.report.IFileWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;


public class JsonFileWriter implements IFileWriter {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void write(List data, String outputPath) {
        try {
            String s = objectMapper.writeValueAsString(data);
            FileWriter fw = new FileWriter(new File(outputPath));
            fw.write(s);
            fw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
