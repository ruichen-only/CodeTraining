package io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

public class ZIPCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("test.zip");
        CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        BufferedOutputStream dos = new BufferedOutputStream(zos);
        zos.setComment("A test of java zipping");
        System.out.println("Writing file : " + "test.txt");
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        zos.putNextEntry(new ZipEntry("test.txt"));
        int c;
        while((c = br.read()) != -1) {
            dos.write(c);
        }
        br.close();
        dos.flush();
        dos.close();

        System.out.println("Checksum:" + cos.getChecksum().getValue());
        System.out.println("Reading files");
        FileInputStream fs = new FileInputStream("test.zip");
        CheckedInputStream cis = new CheckedInputStream(fs, new Adler32());
        ZipInputStream zis = new ZipInputStream(cis);
        BufferedInputStream bis = new BufferedInputStream(zis);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println("Reading file: " + entry);
            int x;
            while ((x = bis.read()) != -1) {
//                System.out.println((char) x);
            }
        }
        bis.close();
        ZipFile zipFile = new ZipFile("test.zip");
        Enumeration enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
            System.out.println(zipEntry);
        }

    }
}
