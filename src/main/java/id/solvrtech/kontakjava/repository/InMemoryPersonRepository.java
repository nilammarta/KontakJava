package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.service.PersonService;

import java.util.ArrayList;

/**
 * class implementation of person repository, and storing the persons data in ArrayList
 */
public class InMemoryPersonRepository implements PersonRepository {
    // create the variable with type ArrayList of persons
    private final ArrayList<Person> persons = new ArrayList<>();

    // create new Person Service
    private PersonService personService = new PersonService();


    // implement all abstract method on interface
    public ArrayList<Person> getAll() {
        return persons;
    }

    public Person getById(int id) {
        return personService.getById(persons, id);
    }

    public ArrayList<Person> getByName(String name) {
        // belum di terapkan
        return persons;
    }

    public ArrayList<Person> getByPhone(String phone){
        // belum selesai
        return persons;
    }

    public Person create(Person person) {
        persons.add(person);
        return person;
    }

    public Person update(Person personEdit) {
        Person person = getById(personEdit.getId());
        person.setName(personEdit.getName());
        person.setPhoneNumber(personEdit.getPhoneNumber());
        return person;
    }

    public void deleteById(int id) {
        persons.remove(id);
    }
}
