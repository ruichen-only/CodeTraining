package io;

import java.io.IOException;
import java.io.StringReader;

public class MemoryInput {
    public static void main(String[] args) throws IOException {
        String file = "D:\\Learning\\workspace\\restart\\ocr.py";
        StringReader in = new StringReader(BufferedInputFile.read(file));
        int c;
        while ((c = in.read()) != -1){
            System.out.println((char) c);
        }
    }
}
