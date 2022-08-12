package com.kirillsheremet.task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String charactersEN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String charactersRU = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        File[] files = new File[10];
        File mergeFile = new File("D:\\B1Task1\\merge.txt");
        mergeFile.createNewFile();
        Random random = new Random();
        DAO dao = new DAO();
        FileWriter writer;
        Scanner sc = new Scanner(System.in);
        ParseFile parseFile = new ParseFile();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        for (int j = 0; j < 10; j++) {

            files[j] = new File("D:\\B1Task1\\test" + j + ".txt");

            if (files[j].createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exist");
            }
            writer = new FileWriter(files[j]);

            for (int i = 0; i < 100000; i++) {


                writer.write(randomDate() + "||"
                        + generateString(random, charactersEN, 10) + "||"
                        + generateString(random, charactersRU, 10) + "||"
                        + (random.nextInt((100_000_000 - 1) + 1) + 1) + "||"
                        + new DecimalFormat("#0.00000000", otherSymbols).format(random.nextDouble((20 - 1) + 1) + 1) + "||"
                        + "\n");
                writer.flush();
            }

        }
        System.out.println("Enter the line to be deleted:");
        String deletedLine = sc.nextLine();
        MergeFiles.mergeFiles(files, mergeFile, deletedLine);
        System.out.println("The number of deleted rows is: " + DeleteFromFile.numberOfDeletedRows);
        dao.insertIntoDB(parseFile.parsingFile(files[0]));
        System.out.println("The sum of the integer numbers is: " + dao.getSumIntegerNumberFromDatabase());
        System.out.println("The median of the float numbers is: " + dao.getMedianFloatNumberFromDatabase());
    }


    public static String randomDate() {

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2017, 2022);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
