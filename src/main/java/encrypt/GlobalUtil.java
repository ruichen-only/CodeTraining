package encrypt;

public class GlobalUtil {
    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    /**
     * Convert byte array to string.
     * @param digest
     * @return
     */
    protected static String byteArrayToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < digest.length; i++) {
            sb.append(byteToHexString(digest[i]));
        }
        return sb.toString();
    }

    /**
     * Convert byte to string.
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if(n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }
}
