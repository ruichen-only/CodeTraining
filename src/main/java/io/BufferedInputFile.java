package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Learning\\workspace\\restart");
        String [] fileList = file.list();
        for(String name :fileList){
            System.out.println(name);
        }
        System.out.println(read("D:\\Learning\\workspace\\restart\\ocr.py"));
    }

    public static String read(String file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }
}
