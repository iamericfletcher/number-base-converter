package converter;

public class ArrayUtilFraction {

    public static String[] numberToArray(String numberToConvert) {

        String[] numberToConvertArray = String.valueOf(numberToConvert).split("");

        String[] possibleValues = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };

        StringBuilder outputValues = new StringBuilder();
        int count = 0;

        for (String number : numberToConvertArray) {
            for (String value : possibleValues) {
                if (number.equalsIgnoreCase(value)) {
                    outputValues.append(count).append(" ");
                    break;
                } else {
                    count = count + 1;
                }
            }
            count = 0;
        }
        return outputValues.toString().split(" ");
    }


}
