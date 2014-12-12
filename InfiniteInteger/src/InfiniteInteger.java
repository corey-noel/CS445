public class InfiniteInteger implements Comparable<InfiniteInteger> {

    private boolean isNegative;
    private int[] digits;

    /**
     * Constructor: Constructs this infinite integer from a string
     * representing an integer.
     * @param s  a string represents an integer
     */
    public InfiniteInteger(String s) {
        s = s.replaceAll("\\s", "");

        if (s.isEmpty()) {
            isNegative = false;
            digits = new int[] {0};
            return;
        }

        if (s.charAt(0) == '-') {
            isNegative = true;
            s = s.substring(1);
        } else
            isNegative = false;

        char[] chars = s.toCharArray();
        digits = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isDigit(chars[i]))
                throw new IllegalArgumentException("Only numbers and '-' allowed.");

            digits[digits.length - 1 - i] = Integer.parseInt(chars[i] + "");
        }
        removeLeadingZeros();
    }

    /**
     * Constructor: Constructs this infinite integer from an integer.
     * @param anInteger  an integer
     */
    public InfiniteInteger(int anInteger) {
        isNegative = anInteger < 0;

        anInteger = Math.abs(anInteger);
        digits = new int[Math.max((int) Math.ceil(Math.log10(anInteger + 1)), 1)];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = (anInteger % 10);
            anInteger /= 10;
        }
        removeLeadingZeros();
    }

    /**
     * Constructor: Constructs this infinite integer from a int array
     * of digits and a boolean indicating if it is negative
     * @param dig int array of digits, each between 0-9
     * @param negative whether or not this integer is negative
     */
    public InfiniteInteger(int[] dig, boolean negative) {
        digits = dig;
        isNegative = negative;
        removeLeadingZeros();
    }

    /**
     * Gets the number of digits of this infinite integer.
     * @return an integer representing the number of digits
     * of this infinite integer.
     */
    public int getNumberOfDigits() { return digits.length; }

    /**
     * Checks whether this infinite integer is a negative number.
     * @return true if this infinite integer is a negative number.
     * Otherwise, return false.
     */
    public boolean isNegative() { return isNegative; }

    /**
     * Calculates the result of this infinite integer plus a
     * @param a the infinite integer to be added to this
     * infinite integer.
     * @return a NEW infinite integer representing the result of this
     * infinite integer plus a
     */
    public InfiniteInteger plus(InfiniteInteger a) {
        InfiniteInteger large, small;
        if (this.compareSize(a) >= 0) {
            large = this;
            small = a;
        } else {
            large = a;
            small = this;
        }

        if (this.isNegative) {
            if (a.isNegative)
                return new InfiniteInteger(plus2(large.digits, small.digits), true);
            else {
                if (this == large)
                    return new InfiniteInteger(minus2(large.digits, small.digits), true);
                else
                    return new InfiniteInteger(minus2(large.digits, small.digits), false);
            }
        } else {
            if (a.isNegative) {
                if (this == large)
                    return new InfiniteInteger(minus2(large.digits, small.digits), false);
                else
                    return new InfiniteInteger(minus2(large.digits, small.digits), true);
            } else
                return new InfiniteInteger(plus2(large.digits, small.digits), false);
        }
    }

    /**
     * Calculates the result of this infinite integer subtracted by a
     * @param a the infinite integer to subtract.
     * @return a NEW infinite integer representing the result of this
     * infinite integer subtracted by a
     */
    public InfiniteInteger minus(InfiniteInteger a) {
        InfiniteInteger large, small;
        if (this.compareSize(a) >= 0) {
            large = this;
            small = a;
        } else {
            large = a;
            small = this;
        }

        if (this.isNegative) {
            if (a.isNegative) {
                if (this == large)
                    return new InfiniteInteger(minus2(large.digits, small.digits), true);
                else
                    return new InfiniteInteger(minus2(large.digits, small.digits), false);
            } else
                return new InfiniteInteger(plus2(large.digits, small.digits), true);
        } else {
            if (a.isNegative) {
                return new InfiniteInteger(plus2(large.digits, small.digits), false);
            } else {
                if (this == large)
                    return new InfiniteInteger(minus2(large.digits, small.digits), false);
                else
                    return new InfiniteInteger(minus2(large.digits, small.digits), true);
            }
        }
    }

    private static int[] plus2(int[] large, int[] small) {
        int[] result = new int[large.length + 1];

        for (int i = 0; i < result.length; i++) {
            if (i < small.length)
                result[i] = small[i] + large[i];
            else if (i < large.length)
                result[i] = large[i];
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] > 9) {
                result[i] -= 10;
                result[i + 1] += 1;
            }
        }

        return result;
    }

    private static int[] minus2(int[] large, int[] small) {
        int[] result = new int[large.length];

        for (int i = 0; i < result.length; i++) {
            if (i < small.length)
                result[i] = large[i] - small[i];
            else if (i < large.length)
                result[i] = large[i];
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] < 0) {
                result[i] += 10;
                result[i + 1] -= 1;
            }
        }

        return result;
     }


    /**
     * Calculates the result of this infinite integer multiplied by a
     * @param a the multiplier.
     * @return a NEW infinite integer representing the result of this
     * infinite integer multiplied by a.
     */
    public InfiniteInteger multiply(InfiniteInteger a) {
        int[] result = new int[this.digits.length + a.digits.length];

        InfiniteInteger large, small;
        if (compareSize(a) >= 0) {
            large = this;
            small = a;
        } else {
            large = a;
            small = this;
        }

        for (int i = 0; i < large.digits.length; i++) {
            for (int j = 0; j < small.digits.length; j++) {
                result[i + j] += large.digits[i] * small.digits[j];
            }
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] > 9) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
        }

        return new InfiniteInteger(result, this.isNegative ^ a.isNegative);
    }

    /**
     * Generates a string representing this infinite integer. If this infinite integer
     * is a negative number a minus symbol should be in the front of numbers. For example,
     * "-12345678901234567890". But if the infinite integer is a positive number, no symbol
     * should be in the front of the numbers (e.g., "12345678901234567890").
     * @return a string representing this infinite integer number.
     */
    public String toString() {
        String result = "";
        if (this.isNegative)
            result = result.concat("-");
        for (int i = digits.length - 1; i >= 0; i--)
            result = result.concat(digits[i] + "");

        return result;
    }

    /**
     * Compares this infinite integer with anInfiniteInteger
     * @param a the InfiniteInteger to be compared to
     * @return either -1, 0, or 1 as follows:
     * If this infinite integer is less than a, return -1.
     * If this infinite integer is equal to a, return 0.
     * If this infinite integer is greater than a, return 1.
     */
    public int compareTo(InfiniteInteger a) {
        int negate = 1;

        if (this.isNegative) {
            if (a.isNegative)
                negate = -1;
            else
                return -1;
        } else if (a.isNegative)
            return 1;

        return negate * compareSize(a);
    }

    public int compareSize(InfiniteInteger a) {
        if(this.digits.length > a.digits.length)
            return 1;
        else if (this.digits.length < a.digits.length)
            return -1;

        for (int i = this.digits.length - 1; i >= 0; i--)
            if (this.digits[i] > a.digits[i])
                return 1;
            else if (this.digits[i] < a.digits[i])
                return -1 ;

        return 0;
    }

    private void removeLeadingZeros() {
        if (digits[digits.length - 1] == 0) {
            int numZeros = 1;
            for (int i = digits.length - 2; i >= 0; i--) {
                if (digits[i] == 0)
                    numZeros++;
                else
                    break;
            }

            if (digits.length == numZeros)
                digits = new int[] {0};
            else {
                int[] temp = new int[digits.length - numZeros];
                System.arraycopy(digits, 0, temp, 0, temp.length);
                digits = temp;
            }
        }

        if (digits.length == 1 && digits[0] == 0)
            isNegative = false;
    }
}