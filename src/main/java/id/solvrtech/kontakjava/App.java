package id.solvrtech.kontakjava;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.repository.InMemoryPersonRepository;
import id.solvrtech.kontakjava.service.PersonService;

import java.util.ArrayList;
import java.util.Scanner;

import static id.solvrtech.kontakjava.utils.Helper.*;

/**
 * class for run menu of kontak java app
 */
public class App {
    // showing menu of kontak java app
    public void showMenu() {
        System.out.println("=== KONTAK JAVA ===");
        System.out.println("1. Show All Persons");
        System.out.println("2. Create New Person");
        System.out.println("3. Edit Person");
        System.out.println("4. Delete Person");
        System.out.println("5. Exit");
    }

    // run method
    public void run() {
        // create new InMemoryPersonRepository
//        InMemoryPersonRepository repository = new InMemoryPersonRepository();
        // create new personService
        PersonService personService = new PersonService();

        while (true) {
            // call the showMenu method
            showMenu();

            Integer menuChoice = readInputAsInt("Your choice: ");

            if (menuChoice == 1) {
                System.out.println("=== ALL KONTAK PERSONS ===");
                ArrayList<Person> persons = personService.getAll();
                showData(persons);

                pressEnterToContinue();

            } else if (menuChoice == 2) {
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

            } else if (menuChoice == 3) {
                System.out.println("=== EDIT PERSON ===");
                ArrayList<Person> persons = showData(personService.getAll());

                if (persons.size() != 0) {
                    int editPerson = readInputAsInt("Choose a person to edit: ");
                    Person thePerson = persons.get((editPerson - 1));

                    // cek apakah data sudah benar
                    System.out.println(thePerson.getName() + ": " + thePerson.getPhoneNumber());

                    if (thePerson != null) {
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
                        System.out.println(" ");
                    }
                }else{
                    pressEnterToContinue();
                }

            }else if (menuChoice == 4) {
                System.out.println("=== DELETE PERSON ===");
            }else if (menuChoice == 5) {
                System.out.println("=== EXIT ===");
                System.out.println("See you soon!");
                break;
            }
        }
    }
}
