package converter;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FromToAnyBaseDecimalFraction {

    public static String convertToDecimalInteger(int sourceBase, int targetBase, String integer) {

        String[] integerToConvertArray = ArrayUtilNoFraction.numberToArray(integer);

        BigInteger integerToDecimalBig = BigInteger.ZERO;

        int basePowers = integerToConvertArray.length - 1;

        for (String s : integerToConvertArray) {
            integerToDecimalBig = integerToDecimalBig
                    .add(BigInteger.valueOf(Integer.parseInt(s))
                            .multiply(BigInteger.valueOf(sourceBase).pow(basePowers)));
            basePowers = basePowers - 1;
        }
        return convertToTargetBase(integerToDecimalBig, targetBase);
    }


    public static String convertToDecimalFraction(int sourceBase, int targetBase, String fraction) {

        String[] fractionToConvertArray = ArrayUtilFraction.numberToArray(fraction);

        int basePowers = 1;

        BigDecimal fractionToDecimalBig = BigDecimal.ZERO;



        for (String s : fractionToConvertArray) {
            fractionToDecimalBig = fractionToDecimalBig
                    .add(BigDecimal.valueOf(Long.parseLong(s))
                            .divide(BigDecimal.valueOf(sourceBase).pow(basePowers), 20, RoundingMode.HALF_UP));
            basePowers = basePowers + 1;
        }


        BigDecimal integerRemainderAmount = fractionToDecimalBig.multiply(BigDecimal.valueOf(targetBase));
        StringBuilder integerRemainderOutput = new StringBuilder();
        integerRemainderOutput.append(integerRemainderAmount.setScale(0, RoundingMode.FLOOR)).append(" ");

        int count2 = 1;


        while (count2 != 5) {
            if (integerRemainderAmount.compareTo(BigDecimal.ZERO) == 0) {
                integerRemainderOutput.append(0).append(" ");
                count2 = count2 + 1;
            } else {
                if (integerRemainderAmount.compareTo(BigDecimal.ONE) >= 0) {

                    String val = integerRemainderAmount.toString();
                    String[] arr = val.split("\\.");
                    String integerPortion = arr[0];
                    BigDecimal integerPortionBig = new BigDecimal(integerPortion);

                    integerRemainderAmount = integerRemainderAmount.subtract(integerPortionBig);

                } else {
                    integerRemainderAmount = integerRemainderAmount.multiply(BigDecimal.valueOf(targetBase));
                    integerRemainderOutput.append(integerRemainderAmount.setScale(0, RoundingMode.FLOOR)).append(" ");
                    count2 = count2 + 1;
                }
            }
        }

        String[] integerRemainderOutputStringArray = integerRemainderOutput.toString().split(" ");
        int[] integerRemainderOutputArray = new int[integerRemainderOutputStringArray.length];

        for (int i = 0; i < integerRemainderOutputStringArray.length; i++) {
            integerRemainderOutputArray[i] = Integer.parseInt(integerRemainderOutputStringArray[i]);
        }

        StringBuilder finalOutput = new StringBuilder();

        int count = 0;

        for (int i = 0; i < integerRemainderOutputArray.length; i++) {

            String[] possibleValues = new String[]{
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z"
            };

            while (true) {
                if (count == integerRemainderOutputArray[i]) {
                    finalOutput.append(possibleValues[count]);
                    break;
                } else {
                    count = count + 1;
                }
            }
            count = 0;
        }
        return finalOutput.toString();
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
