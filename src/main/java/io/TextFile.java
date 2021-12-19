package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
    /**
     * Constructor.
     * Read a file, split by any regular expression.
     */
    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        //Regular expression split() often leaves an empty string at the first position.
        if(get(0).equals("")) remove(0);
    }

    /**
     * Constructor.
     * Normally read by lines.
     * @param fileName
     */
    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    /**
     * Write a single file after static method write() has been called.
     * @param fileName
     */
    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                for(String item : this) {
                    out.print(item);
                    out.print("\n");
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Read file as a single string.
     * @param fileName
     * @return
     */
    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(fileName).getAbsoluteFile();
            BufferedReader in = new BufferedReader(new FileReader(file));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }
    return sb.toString();
    }

    /**
     * Write a single file in one method call.
     * @param fileName
     * @param text
     */
    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        String file = read("src\\io\\TextFile.java");
        write("test.txt", file);
        TextFile textFile = new TextFile("test.txt");
        textFile.write("text2.txt");

        TreeSet<String> words = new TreeSet<String>(new TextFile("src\\io\\TextFile.java", "\\W+"));
        System.out.println(words.headSet("a"));
    }
}
