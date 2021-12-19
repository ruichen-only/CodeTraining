package util;

public class GlobalUtil {
    public static short byte2Short(byte value) {
        return (short)(value & 0xff);
    }

    public static byte short2Byte(short value) {
        return (byte)(value & 0xff);
    }
}
