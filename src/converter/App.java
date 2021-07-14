package converter;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner;

        while (true) {

            scanner = new Scanner(System.in);

            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit): ");

            String baseSourceTarget = scanner.nextLine();
            String[] baseSourceTargetArray = baseSourceTarget.split(" ");

            if (baseSourceTarget.equals("/exit")) {
                scanner.close();
                break;
            }

            int sourceBase = Integer.parseInt(baseSourceTargetArray[0]);
            int targetBase = Integer.parseInt(baseSourceTargetArray[1]);

            while (true) {

                System.out.printf("Enter number in base %s to convert to base %s (To go back type /back): ", sourceBase, targetBase);
                String numberToConvert = scanner.next();

                if (numberToConvert.equals("/back")) {
                    System.out.println();
                    break;
                }
                // When user input contains a fractional portion
                if (numberToConvert.contains(".")) {

                    String[] integerFraction = numberToConvert.split("\\.");
                    String integer = integerFraction[0];
                    String fraction = integerFraction[1];

                    System.out.println("Conversion result: " + FromToAnyBaseDecimalFraction.convertToDecimalInteger(sourceBase, targetBase, integer) +
                            "." + FromToAnyBaseDecimalFraction.convertToDecimalFraction(sourceBase, targetBase, fraction) + "\n");

                    // When user input is a whole number
                } else {

                    System.out.println("Conversion result: " + FromToAnyBaseDecimalNoFraction.convertToDecimal(sourceBase, targetBase, numberToConvert) + "\n");

                }
            }
        }
        scanner.close();
    }
}
