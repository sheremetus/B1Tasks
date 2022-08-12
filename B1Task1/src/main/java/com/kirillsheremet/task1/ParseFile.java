package com.kirillsheremet.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {
    private String[] lineFromFile = new String[5];
    private List<String[]> linesFromFiles = new ArrayList<>();

    public List<String[]> parsingFile(File inputFile) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {

            String trimmedLine = currentLine.trim();
            lineFromFile = trimmedLine.split("\\|\\|");
            linesFromFiles.add(lineFromFile);

        }


        reader.close();
        return linesFromFiles;
    }
}
