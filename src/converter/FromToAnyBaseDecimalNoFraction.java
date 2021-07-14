package converter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;


import java.util.List;

public class FromToAnyBaseDecimalNoFraction {

    public static String convertToDecimal(int sourceBase, int targetBase, String numberToConvert) {

        String[] numberToConvertArray = ArrayUtilNoFraction.numberToArray(numberToConvert);

        int basePowers = numberToConvertArray.length - 1;

        BigInteger sourceToDecimalBig = BigInteger.ZERO;

        for (String s : numberToConvertArray) {
            sourceToDecimalBig = sourceToDecimalBig
                    .add(BigInteger.valueOf(Integer.parseInt(s))
                            .multiply(BigInteger.valueOf(sourceBase).pow(basePowers)));
            basePowers = basePowers - 1;
        }
        return convertToTargetBase(sourceToDecimalBig, targetBase);
    }

    public static String convertToTargetBase(BigInteger sourceToDecimalBig, int targetBase) {

        String[] possibleValues = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };

        BigInteger quotientBig = sourceToDecimalBig.divide(BigInteger.valueOf(targetBase));
        BigInteger currentRemainderDigitBig = sourceToDecimalBig.mod(BigInteger.valueOf(targetBase));

        StringBuilder remainderDigits = new StringBuilder();

        remainderDigits.append(currentRemainderDigitBig).append(" ");

        while (!quotientBig.equals(BigInteger.ZERO)) {
            currentRemainderDigitBig = quotientBig.mod(BigInteger.valueOf(targetBase));
            quotientBig = quotientBig.divide(BigInteger.valueOf(targetBase));

            remainderDigits.append(currentRemainderDigitBig).append(" ");
        }

        String[] remainderDigitsArray = remainderDigits.toString().split(" ");

        List<String> list = Arrays.asList(remainderDigitsArray);
        Collections.reverse(list);

        StringBuilder outputValues = new StringBuilder();

        for (String remainderDigit : list) {
            outputValues.append(possibleValues[Integer.parseInt(remainderDigit)]);
        }
        return outputValues.toString();
    }
}
