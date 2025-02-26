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
        InMemoryPersonRepository repository = new InMemoryPersonRepository();
        // create new personService
        PersonService  personService = new PersonService();

        while (true) {
            // call the showMenu method
            showMenu();

            Integer menuChoice = readInputAsInt("Your choice: ");

            if (menuChoice == 1) {
                System.out.println("=== ALL KONTAK PERSONS ===");
                ArrayList<Person> persons = repository.getAll();

                for (Person person : persons) {
                    System.out.println("Name: " + person.getName());
                    System.out.println("Phone number: " + person.getPhoneNumber());
                    System.out.println(" ");
                }

            }else if (menuChoice == 2) {
                System.out.println("=== CREATE NEW PERSON ===");

                String name = readInputAsString("Name: ");
                int phoneNumber = readInputAsInt("Phone number: ");

                Person newPerson = personService.create(repository.getAll(), name, phoneNumber);
                repository.create(newPerson);

                System.out.println("New person has been created!");
                pressEnterToContinue();
            }
        }
    }
}
