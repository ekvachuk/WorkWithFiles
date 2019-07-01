package org.itstep.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerserviceTest {

    private static String jpgFileFromPath;
    private static String jpgFileToPath;
    private static String txtFileFromPath;
    private static String txtFileToPath;

    @BeforeAll
    static void setPreData() {

        String MAIN_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");


        jpgFileFromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "cat.jpg";
        jpgFileToPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "cat_test_copy.jpg";
        txtFileFromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text.txt";
        txtFileToPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text_test_copy.txt";
    }


    @Test
    void testGetFileAsByteArray() {
        byte[] bytes = FileManagerservice.getFileAsByteArray(jpgFileFromPath);
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }

    @Test
    void testWriteByteArrayToFile() {
        byte[] bytes = FileManagerservice.getFileAsByteArray(jpgFileFromPath);

        FileManagerservice.writeByteArrayToFile(bytes, jpgFileToPath);
        byte[] testBytes = FileManagerservice.getFileAsByteArray(jpgFileFromPath);

        assertNotNull(testBytes);
        assertTrue(testBytes.length > 0);
    }

    @Test
    void testWriteTextToFileWithoutAppend() {

        String text = "some test text\n" +
                "need to be writen to file.";

        FileManagerservice.writeTextToFile(txtFileToPath, text, false);

        String testText = FileManagerservice.getTextFromFile(txtFileToPath);
        assertNotNull(testText);
        assertEquals(testText, text + "\n");

        FileManagerservice.writeTextToFile(txtFileToPath, text, false);

        testText = FileManagerservice.getTextFromFile(txtFileToPath);
        assertNotNull(testText);
        assertEquals(testText, text + "\n");
    }

    @Test
    void testWriteTextToFileWithAppend() {

        String text = "some test text\n" +
                "need to be writen to file.";

        FileManagerservice.writeTextToFile(txtFileToPath, text, false);

        String testText = FileManagerservice.getTextFromFile(txtFileToPath);
        assertNotNull(testText);
        assertEquals(testText, text + "\n");

        FileManagerservice.writeTextToFile(txtFileToPath, text, true);

        testText = FileManagerservice.getTextFromFile(txtFileToPath);
        assertNotNull(testText);
        assertEquals(testText, text + "\n" + text + "\n");
    }

        @Test
        void testGetFileAsByteArrayTXT() {
            String text = FileManagerservice.getTextFromFile(txtFileFromPath);

            assertNotNull(text);
            assertTrue(text.contains("someLine1\n"));
            assertTrue(text.contains("someLine2\n"));
            assertTrue(text.contains("someLine3\n"));
        }

            @AfterAll
            static void removeTestFiles() {
            File jpgFile = new File(jpgFileToPath);
            File txtFile = new File(txtFileToPath);
            if (jpgFile.isFile()) {

                try {
                    Files.delete(jpgFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (jpgFile.isFile()) {
                    try {
                        Files.delete(txtFile.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        }

