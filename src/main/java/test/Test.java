package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author CRR
 */
public class Test {
    private static final int BOM_HEADER_SIZE = 3;
    private static final byte[] UTF_8_BOM = new byte[]{-17, -69, -65};

    public static void main(String[] args) {
        System.out.println("123");
        System.err.println("123");
    }

    private static boolean isBOMFile(InputStream is) {
        byte[] bomHeader = new byte[3];
        try {
            int len = is.read(bomHeader);
            if(len < BOM_HEADER_SIZE) {
                return false;
            }

            if(Arrays.equals(bomHeader, UTF_8_BOM)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
