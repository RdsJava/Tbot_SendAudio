package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;


public class RenameFile {

    public void renameFile(String fileName, String fileTime, String end) {

        File file;
        File dest = null;
        try {

            // исходный файл
            file = new File(fileName);
            if (!file.exists()) {
                throw new NoSuchFileException("The source file does not exist.");
            }

            // целевой файл

            dest = new File(file + fileTime + end);
            if (dest.exists()) {
                throw new FileAlreadyExistsException("The destination path exists. Конечный путь существует ");
            }

            boolean success = file.renameTo(dest);
            if (success) {
                System.out.println("File successfully renamed");
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
