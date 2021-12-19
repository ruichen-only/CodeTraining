package util;

import java.math.BigDecimal;

public class Math {
    //constant used for pi computation
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    //rounding mode to use during pi computation
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

    /**
     * Compute the value of pi to the specified number of digits after the
     * decimal point.The value is computed using Machin's formula:
     * pi/4 = 4 * arctan(1/5) - arctan(1/239)
     * and power series expansion of arctan(x) to sufficient precision.
     * @param digits
     * @return
     */
    private static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5   = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
        return pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Compute the value, in radians, of the arc tangent of the inverse of
     * the supplied integer to the specified number of digits after the
     * decimal point.The value is compute using the power series
     * expansion for the arc tangent:
     * arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 + (x^9)/9...
     * @param inverseX
     * @param scale
     * @return
     */
    private static BigDecimal arctan(int inverseX, int scale) {
        BigDecimal result, number, term;
        BigDecimal invX = BigDecimal.valueOf(inverseX);
        BigDecimal invX2 = BigDecimal.valueOf(inverseX * inverseX);
        number = BigDecimal.ONE.divide(invX, scale, ROUNDING_MODE);
        result = number;
        int i = 1;
        do {
            number = number.divide(invX2, scale, ROUNDING_MODE);
            int denom = 2 * i + 1;
            term = number.divide(BigDecimal.valueOf(denom), scale, ROUNDING_MODE);
            if((i % 2) != 0)
                result = result.subtract(term);
            else
                result = result.add(term);
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(computePi(50000));
    }
}
