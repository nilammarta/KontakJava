package id.solvrtech.kontakjava;

import id.solvrtech.kontakjava.utils.MysqlConnection;
import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.service.PersonService;

import java.sql.Connection;
import java.util.ArrayList;

import static id.solvrtech.kontakjava.utils.Helper.*;
import static id.solvrtech.kontakjava.utils.MysqlConnection.getConnection;

/**
 * class for run menu of kontak java app
 */
public class App {
    // showing menu of kontak java app
    public void showMenu() {
        System.out.println("=== KONTAK JAVA ===");
        System.out.println("1. Show All Persons");
        System.out.println("2. Search Person");
        System.out.println("3. Create New Person");
        System.out.println("4. Edit Person");
        System.out.println("5. Delete Person");
        System.out.println("6. Exit");
    }

    // run method
    public void run() {
        // create new personService
        PersonService personService = new PersonService();


        while (true) {
            // call the showMenu method
            showMenu();

            int menuChoice = readInputAsInt("Your choice: ");

            if (menuChoice == 1) {
                System.out.println("=== ALL KONTAK PERSONS ===");
                ArrayList<Person> persons = personService.getAll();
                showData(persons);

                pressEnterToContinue();

            } else if (menuChoice == 2) {
                System.out.println("=== SEARCH PERSON ===");
                String searchInput = readInputAsString("Enter your name or your: ");

                ArrayList<Person> searchResult = personService.searchPerson(searchInput);
                if (searchInput != null) {
                    showData(searchResult);
                    pressEnterToContinue();
                }else{
                    System.out.println("Search not found!");
                    pressEnterToContinue();
                }

            } else if (menuChoice == 3) {
                System.out.println("=== CREATE NEW PERSON ===");

                boolean valid = true;
                while (valid) {
                    String name = readInputAsString("Name: ");

                    if (!name.isEmpty()) {
                        while (valid) {
                            String phoneNumber = readInputAsString("Phone number: ");

                            if (isPhoneNumberExits(personService.getAll(), phoneNumber) == true) {
                                System.out.println("Your phone number is already exists, please input another number!");

                            } else if (isInputNumeric(phoneNumber)) {
                                personService.create(name, phoneNumber);

                                System.out.println(" ");
                                System.out.println("New person has been created!");

                                valid = false;
                            } else {
                                System.out.println("Invalid phone number!");
                                System.out.println(" ");
                            }
                        }
                    } else {
                        System.out.println("Invalid name!");
                        System.out.println(" ");
                    }
                }

                pressEnterToContinue();

            } else if (menuChoice == 4) {
                System.out.println("=== EDIT PERSON ===");
                ArrayList<Person> persons = showData(personService.getAll());

                if (persons.size() != 0) {
                    int id = readInputAsInt("Choose the ID of the person to edit: ");

                    Person thePerson = personService.getById(id);

                    if (thePerson != null) {
                        // Tampilkan data yang sudah dipilih
                        System.out.println("=== Person Data ===");
                        System.out.println("Nama         : " + thePerson.getName());
                        System.out.println("Phone number : " + thePerson.getPhoneNumber());
                        System.out.println(" ");

                        System.out.println("=== INPUT NEW DATA ===");
                        boolean valid = true;
                        while (valid) {
                            String newName = readInputAsString("Name: ");

                            if (!newName.isEmpty()) {
                                while (valid) {
                                    String newPhoneNumber = readInputAsString("Phone number: ");

                                    // validasi jika ada number yang sama dengan data yang lain
                                    if (isPhoneNumberExits(personService.getAll(), newPhoneNumber, thePerson.getId()) == true) {
                                        System.out.println("Your phone number is already exists, please input another number!");
                                    } else if (isInputNumeric(newPhoneNumber)) {
                                        personService.update(thePerson, newName, newPhoneNumber);
                                        System.out.println(" ");
                                        System.out.println("New person has been updated!");
                                        pressEnterToContinue();
                                        valid = false;
                                    } else {
                                        System.out.println("Invalid phone number!");
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Person data not found!");
                        pressEnterToContinue();
                    }
                }else{
                    pressEnterToContinue();
                }

            }else if (menuChoice == 5) {
                System.out.println("=== DELETE PERSON ===");
                ArrayList<Person> persons = showData(personService.getAll());

                if (persons.size() != 0) {
                    int id = readInputAsInt("Choose the ID of the person to delete: ");
                    Person thePerson = personService.getById(id);

                    // Tampilkan data yang sudah dipilih
                    System.out.println("=== Person Data ===");
                    System.out.println("Nama         : " + thePerson.getName());
                    System.out.println("Phone number : " + thePerson.getPhoneNumber());
                    System.out.println(" ");
                    String confirm = readInputAsString("Are you sure you want to delete this person? (y/n): ");

                    if (confirm.equals("y")) {
                        String deleted = personService.delete(id);
                        System.out.println(deleted);
                        System.out.println(" ");
                    } else if (confirm.equals("n")) {
                        System.out.println("Cancel deletion!");
                    } else {
                        System.out.println("Please input 'y' or 'n' to continue!");
                    }
                }

            }else if (menuChoice == 6) {
                System.out.println("=== EXIT ===");
                System.out.println("See you soon!");
                break;
            }else {
                System.out.println("PLease input the correct option!");
                pressEnterToContinue();
            }
        }
    }
}
