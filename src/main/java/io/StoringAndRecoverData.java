package io;

import java.io.*;

public class StoringAndRecoverData {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("Data.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(3.1415926);
        dos.writeUTF("That is a pi");
        dos.writeInt(2);
        dos.writeUTF("two");
        dos.close();
        FileInputStream fis = new FileInputStream("Data.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);
        System.out.println(dis.readDouble());
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readUTF());
    }
}
