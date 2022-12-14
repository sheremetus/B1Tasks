package com.kirillsheremet.task1;

import java.io.*;

public class MergeFiles {

    public static void mergeFiles(File[] files, File mergedFile, String text) throws IOException {

        FileWriter fstream;
        BufferedWriter out = null;
        try {
            // Оборачиваем поток записи
            fstream = new FileWriter(mergedFile);
            out = new BufferedWriter(fstream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//Перебираем все файлы И!!! параллельно удаляем из них то что нужно
        for (File f : files) {
            System.out.println("merging: " + f.getName());
            f = DeleteFromFile.deleteStringFromFile(f, text);
            FileInputStream fis;
            try {
                fis = new FileInputStream(f);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));

                String aLine;
                while ((aLine = in.readLine()) != null) {
                    out.write(aLine);
                    out.newLine();
                }

                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
