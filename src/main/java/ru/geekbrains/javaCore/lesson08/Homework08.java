package ru.geekbrains.javaCore.lesson08;

import java.io.*;
import java.util.Random;

// Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
// Написать метод, «склеивающий» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
// * Написать метод, который проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).

public class Homework08 {

    public static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        createRandFile("rand1.txt", 50);
        createRandFile("rand2.txt", 50);
        concatFiles("rand1.txt", "rand2.txt", "concat.txt");

        // просмотриваем содержимое рабочей директории
        File file = new File(".");
        // список файлов и директорий
        String[] filesList = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                // интересуют файлы с любым расширением
                return name.matches(".+\\..*");
            }
        });
        for (String s : filesList) {
            // Строка 'xml' обнаружена в файле 'homework.iml'.
            // Строка 'xml' обнаружена в файле 'pom.xml'.
            inFile(s, "xml");
        }
    }

    public static void createRandFile(String filePath, int fileSize) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath, false);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        for (int i = 0; i < fileSize; i++)
            bos.write((byte)0x30 + rand.nextInt(0x49));
        bos.flush();
        bos.close();
    }

    public static void concatFiles(String filePath1, String filePath2, String concatFilePath) throws IOException {
        File file1 = new File(filePath1);
        if (!file1.exists() || !file1.isFile()) {
            System.out.println("Файл " + filePath1 + " не существует.");
            return;
        }
        File file2 = new File(filePath2);
        if (!file2.exists() || !file2.isFile()) {
            System.out.println("Файл " + filePath2 + " не существует.");
            return;
        }
        // файл источник №1
        FileInputStream fis1 = new FileInputStream(filePath1);
        BufferedInputStream bis1 = new BufferedInputStream(fis1);

        // файл источник №2
        FileInputStream fis2 = new FileInputStream(filePath2);
        BufferedInputStream bis2 = new BufferedInputStream(fis2);

        // файл назначения
        FileOutputStream fos = new FileOutputStream(concatFilePath, false);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int b;
        while ((b = bis1.read()) != -1)
            bos.write((byte)b);
        while ((b = bis2.read()) != -1)
            bos.write((byte)b);

        bos.flush();
        bos.close();
    }

    public static void inFile(String filePath, String pattern) throws IOException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Файл " + filePath + " не существует.");
            return;
        }
        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fis);

        int position = 0;
        byte[] patternBytes = pattern.getBytes();
        int b;
        while ((b = bis.read()) != -1) {
            if (b != patternBytes[position])
                position = 0;
            // обнаружено совпадение символа
            if (b == patternBytes[position]) {
                position++;
                if (position == patternBytes.length) {
                    System.out.println("Строка '" + pattern + "' обнаружена в файле '" + filePath + "'.");
                    return;
                }
            }
          }
        System.out.println("Файл '" + filePath + "' не содержит строку '" + pattern + "'.");
    }
}
