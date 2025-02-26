package id.solvrtech.kontakjava.utils;

import id.solvrtech.kontakjava.model.Person;

import java.util.ArrayList;
import java.util.Scanner;

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
            System.err.println("Error while reading input");
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

            return Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Error while reading input");
            pressEnterToContinue();
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

    public static void showData(ArrayList<Person> persons) {
        if(persons.size() != 0) {
            int i = 1;
            for (Person person : persons) {
                System.out.print(i + ". ");
                System.out.println("Name        : " + person.getName());
                System.out.println("   Phone number: " + person.getPhoneNumber());
                System.out.println(" ");
                i++;
            }
        } else {
            System.out.println("Empty Data!");
        }
    }

}
