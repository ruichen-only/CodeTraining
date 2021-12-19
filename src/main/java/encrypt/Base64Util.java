package encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Util extends GlobalUtil {
    private static final String UTF_8 = "UTF-8";

    /**
     * Encrypt by base 64.
     * @param input
     * @return
     */
    public static String encodeData(String input) {
        try {
            if(input == null) return null;
            return new BASE64Encoder().encodeBuffer(input.getBytes(UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decode data.
     * @param inputData
     * @return
     */
    public static String decodeData(String inputData) {
        try {
            if(inputData == null) return null;

            return byteArrayToHexString(new BASE64Decoder().decodeBuffer(inputData));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
