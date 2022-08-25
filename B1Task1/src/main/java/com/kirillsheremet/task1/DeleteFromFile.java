package com.kirillsheremet.task1;

import java.io.*;

public class DeleteFromFile {
    public static int numberOfDeletedRows;

    public static File deleteStringFromFile(File inputFile, String lineToRemove) throws IOException {

        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
        //Удаляем пробелы в начале и в конце строки
            String trimmedLine = currentLine.trim();
            if (trimmedLine.contains(lineToRemove)) {

                numberOfDeletedRows++;
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close();
        reader.close();
        return tempFile;
    }
}
