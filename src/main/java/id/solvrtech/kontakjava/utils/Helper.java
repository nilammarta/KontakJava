package id.solvrtech.kontakjava.utils;

import id.solvrtech.kontakjava.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class helper for create general method
 */
public class Helper {
    // ask input as string
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

    // ask input as int (for phone number)
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

    // press enter method
    public static void pressEnterToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        System.out.println(" ");
    }

    public static boolean isInputNumeric(String input) {
        if(!input.isEmpty()) {
            return input.chars().allMatch(Character::isDigit);
        }else{
            return false;
        }
    }

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

    // validate the phone number
    public static boolean validatePhoneNumber(String phoneNumber) {

        Pattern pattern = Pattern.compile("^(\\+62|62|0)8[1-9][0-9]{5,12}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
