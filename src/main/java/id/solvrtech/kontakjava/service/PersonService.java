package id.solvrtech.kontakjava.service;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.repository.InMemoryPersonRepository;
import id.solvrtech.kontakjava.repository.PersonRepository;

import java.util.ArrayList;

/**
 * class person service to create CRUD logic of person data
 */
public class PersonService {

    private PersonRepository personRepository = new InMemoryPersonRepository();

    public ArrayList<Person> getAll() {
        return personRepository.getAll();
    }

    // get the person daya based on their id
    public Person getById(int id) {
//        ArrayList<Person> persons = personRepository.getAll();
//        for (Person person : persons) {
//            if (person.getId() == id) {
//                return person;
//            }
//        }
//        return null;
        return personRepository.getById(id);
    }

    // logic of create
    public Person create(String name, String phoneNumber) {

        Person newPerson = new Person();

        if (personRepository.getAll().size() == 0) {
            newPerson.setId(0);
        }else {
            newPerson.setId(personRepository.getAll().size() - 1);
        }

        newPerson.setName(name);

        newPerson.setPhoneNumber(phoneNumber);

        personRepository.create(newPerson);

        return newPerson;
    }

    // logic of update
    public Person update(Person personEdit, String newName, String newPhoneNumber) {
        personEdit.setName(newName);
        personEdit.setPhoneNumber(newPhoneNumber);

        // check if phone number is exitst

        return personRepository.update(personEdit);

    }
    // logic of delete
}
