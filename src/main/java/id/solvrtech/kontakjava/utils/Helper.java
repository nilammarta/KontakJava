package id.solvrtech.kontakjava.utils;

import id.solvrtech.kontakjava.model.Person;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class helper for create general method
 */
public class Helper {
    /**
     * Method for read input from terminal as String
     * @param message
     * @return void
     */
    public static String readInputAsString(String message) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            return scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Error while reading input!");
            pressEnterToContinue();
            return "";
        }
    }

    /**
     * Method for read input from terminal as int
     * @param message
     * @return void
     */
    public static int readInputAsInt(String message) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            String input = scanner.nextLine();
            System.out.println(" ");

            return Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Error while reading input!");
            return -1;
        }
    }

    /**
     * Method for press Enter to continue the menu
     */
    public static void pressEnterToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        System.out.println(" ");
    }

    /**
     * Validate if the input is numeric or not
     * @param input
     * @return boolean, True if it's numeric and otherwise
     */
    public static boolean isInputNumeric(String input) {
        if(!input.isEmpty()) {
            return input.chars().allMatch(Character::isDigit);
        }else{
            return false;
        }
    }

    /**
     * Method helper to showing (print out) data
     * @param persons
     * @return List of Person
     */
    public static List<Person> showData(List<Person> persons) {
        if (persons != null && persons.size() != 0) {
            int i = 1;
            for (Person person : persons) {
                System.out.print(i + ". ");
                System.out.println("Id          : " + person.getId());
                System.out.println("   Name        : " + person.getName());
                System.out.println("   Phone number: " + person.getPhoneNumber());
                System.out.println(" ");
                i++;
            }
        } else {
            System.out.println("Empty Data!");
        }

        return persons;
    }

    /**
     * Method helper to validate the phone number format
     * @param phoneNumber
     * @return boolean, TRUE if the phone number id valid, and otherwise
     */
    public static boolean validatePhoneNumber(String phoneNumber) {

        Pattern pattern = Pattern.compile("^(\\+62|62|0)8[1-9][0-9]{5,12}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
