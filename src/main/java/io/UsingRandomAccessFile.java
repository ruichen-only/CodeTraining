package io;

import java.io.RandomAccessFile;

public class UsingRandomAccessFile {
    private static final String FILE = "rcf.txt";

    public static void main(String[] args) throws Exception {
        RandomAccessFile rcf = new RandomAccessFile(FILE, "rw");
        for(int i = 0; i < 7; i++) {
            rcf.writeDouble(i * 1.41);

        }
        rcf.writeUTF("That the end of file");
        rcf.close();
        display();

        rcf = new RandomAccessFile(FILE, "rw");
        rcf.seek(5 * 8);
        rcf.writeDouble(87.23);
        rcf.close();
        display();
    }

    static void display() throws Exception {
        RandomAccessFile rcf = new RandomAccessFile(FILE, "r");
        for(int i = 0; i < 7; i++) {
            System.out.println(rcf.readDouble());
        }
        System.out.println(rcf.readUTF());
        rcf.close();
    }
}
