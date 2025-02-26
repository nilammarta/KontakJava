package id.solvrtech.kontakjava.service;

import id.solvrtech.kontakjava.model.Person;

import java.util.ArrayList;

/**
 * class person service to create CRUD logic of person data
 */
public class PersonService {

    // get the person daya based on their id
    public Person getById(ArrayList<Person> persons, int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    // logic of create
    public Person create(ArrayList<Person> persons, String name, int phoneNumber) {

        Person newPerson = new Person();

        if (persons.size() == 0) {
            newPerson.setId(0);
        }else {
            newPerson.setId(persons.size() - 1);
        }

        newPerson.setName(name);
        newPerson.setPhoneNumber(phoneNumber);

        return newPerson;
    }
    // logic of update
    // logic of delete
}
