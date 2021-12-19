package io;

import java.io.*;
import java.util.Random;

public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] datas = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };
    private Worm next;
    private char ch;

    public Worm() {
        System.out.println("Default constructor");
    }

    public Worm(int i, char x) {
        System.out.println("Worm constructor: " + i);
        ch = x;
        if(--i > 0) {
            next = new Worm(i, (char)(x + 1));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ch);
        sb.append("(");
        for(Data data : datas) {
            sb.append(data);
        }
        sb.append(")");
        if(next != null) {
            sb.append(next);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Worm worm = new Worm(6, 'a');
        System.out.println("worm = " + worm);

        FileOutputStream fos = new FileOutputStream("Worm.txt");
        ObjectOutputStream oop = new ObjectOutputStream(fos);
        oop.writeObject("Worm storage\n");
        oop.writeObject(worm);
        oop.close();

        FileInputStream fis = new FileInputStream("Worm.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
       String s = (String)ois.readObject();
       Worm rWorm = (Worm) ois.readObject();
       System.out.println(s + "worm: " + rWorm);

    }
}
