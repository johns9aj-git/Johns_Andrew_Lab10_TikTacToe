import java.util.Scanner;

/**
 * A utility class for safe user input handling.
 */
public class SafeInput {

    /**
     * Ensures user input is a non-zero length string.
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.trim().length() == 0); // Trim to avoid invalid inputs like empty spaces
        return retString;
    }

    /**
     * Ensures user input is an integer.
     */
    public static int getInt(Scanner pipe, String prompt) {
        int value;
        while (true) {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                pipe.nextLine(); // Clear input buffer
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                pipe.next(); // Clear invalid input
            }
        }
    }

    /**
     * Ensures user input is a double.
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double value;
        while (true) {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                pipe.nextLine(); // Clear input buffer
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                pipe.next(); // Clear invalid input
            }
        }
    }

    /**
     * Ensures user input is an integer within a specified range.
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int value;
        do {
            value = getInt(pipe, prompt + " [" + low + " - " + high + "]");
            if (value < low || value > high) {
                System.out.println("Out of range. Please enter a value between " + low + " and " + high + ".");
            }
        } while (value < low || value > high);
        return value;
    }

    /**
     * Ensures user input is a double within a specified range.
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double value;
        do {
            value = getDouble(pipe, prompt + " [" + low + " - " + high + "]");
            if (value < low || value > high) {
                System.out.println("Out of range. Please enter a value between " + low + " and " + high + ".");
            }
        } while (value < low || value > high);
        return value;
    }

    /**
     * Ensures user input is a Yes or No confirmation.
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String response;
        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = pipe.nextLine().trim().toLowerCase();
            if (response.equals("y"))
                return true;
            if (response.equals("n"))
                return false;
            System.out.println("Invalid input. Please enter Y or N.");
        } while (true);
    }

    /**
     * Ensures user input matches a specified regex pattern.
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input;
        do {
            System.out.print("\n" + prompt + ": ");
            input = pipe.nextLine();
            if (input.matches(regEx)) {
                return input;
            } else {
                System.out.println("Invalid input format. Please try again.");
            }
        } while (true);
    }

    /**
     * Displays a formatted header with a centered message.
     */
    public static void prettyHeader(String msg) {
        final int WIDTH = 60;
        int padding = (WIDTH - msg.length() - 6) / 2;
        String pad = " ".repeat(padding);

        System.out.println("*".repeat(WIDTH));
        System.out.println("***" + pad + msg + pad + (msg.length() % 2 == 0 ? "" : " ") + "***");
        System.out.println("*".repeat(WIDTH));
    }
}