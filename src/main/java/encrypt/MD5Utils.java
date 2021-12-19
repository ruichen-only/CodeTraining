package encrypt;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Utils extends GlobalUtil {

    /**
     * Encrypt by md5.
     * @param origin
     * @param charsetName
     * @return
     */
    public static String MD5Encode(String origin, String charsetName) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(charsetName == null || charsetName == "") {
//                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));

                BASE64Encoder base64Encoder = new BASE64Encoder();
                resultString = base64Encoder.encode(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
            }
        } catch (Exception e) {}
        return resultString;
    }
}
