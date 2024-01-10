package com.user.command.app.file_handling.house_file.report;

import java.util.List;

public interface IFileWriter {
    public void write(List data, String outputPath);
}
