package io;

import java.io.*;

/**
 * @author CRR
 */
public class BasicFileOutput {
    private static final String inFile  = "D:\\Learning\\workspace\\restart\\server.py";
    private static final String outFile = "D:\\Learning\\workspace\\restart\\write.py";

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader(inFile);
        BufferedReader in = new BufferedReader(reader);

        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        PrintWriter out       = new PrintWriter(writer);
        int lineCount = 1;
        String s;
        try {
            while ((s = in.readLine()) != null) {
                out.println(lineCount++ + s + ":");
            }
            System.out.println(BufferedInputFile.read(inFile));
        } catch (Exception e) {
            System.out.println("occurs an I/O error");
        } finally {
            in.close();
            out.close();
        }
    }
}
