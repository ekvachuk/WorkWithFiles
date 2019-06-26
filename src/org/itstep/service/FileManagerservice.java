package org.itstep.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileManagerservice {

    public static byte[] getFileAsByteArray(String filePath) {
        byte[] bytes = null;

        try {
            File file = new File(filePath);
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bytes;
    }

    public static void writeByteArrayToFile(byte[] bytes, String filePath) {

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String pathFrom, String pathTo) {
        byte[] bytes = getFileAsByteArray(pathFrom);
        writeByteArrayToFile(bytes, pathTo);
    }
}
