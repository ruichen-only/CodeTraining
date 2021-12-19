package io;

import java.io.*;

public class FormattedMemoryInput {
    public static void main(String[] args) throws Exception {
        String file = "D:\\Learning\\workspace\\restart\\ocr.py";
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream reader = new BufferedInputStream(fis);
        DataInputStream in = new DataInputStream(reader);
        while (in.available() != 0) {
            System.out.println((char)in.readByte());
        }
    }
}
