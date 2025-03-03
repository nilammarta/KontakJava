package id.solvrtech.kontakjava.service;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.repository.InMemoryPersonRepository;
import id.solvrtech.kontakjava.repository.MySqlPersonRepository;
import id.solvrtech.kontakjava.repository.PersonRepository;

import java.util.ArrayList;

/**
 * class person service to create CRUD logic of person data
 */
public class PersonService {

//    private PersonRepository personRepository = new InMemoryPersonRepository();
    private final PersonRepository personRepository = new MySqlPersonRepository();

    public ArrayList<Person> getAll() {
        return personRepository.getAll();
    }

    // get the person daya based on their id
    public Person getById(int id) {
        return personRepository.getById(id);
    }

    // logic of create
    public Person create(String name, String phoneNumber) {

        Person newPerson = new Person(name, phoneNumber);

        newPerson.setName(name);

        newPerson.setPhoneNumber(phoneNumber);

        personRepository.create(newPerson);

        return newPerson;
    }

    // logic of update
    public Person update(Person personEdit, String newName, String newPhoneNumber) {
        personEdit.setName(newName);
        personEdit.setPhoneNumber(newPhoneNumber);

        return personRepository.update(personEdit);
    }

    // logic of delete
    public String delete(int id) {
        try {
            personRepository.deleteById(id);
            return "Person data deleted successfully!";
        }catch (Exception e) {
            return "Person data could not be deleted!";
        }
    }

    public ArrayList<Person> searchPerson(String searchInput) {
        ArrayList<Person> personsByName = personRepository.getByName(searchInput);
        ArrayList<Person> personsByPhoneNumber = personRepository.getByPhone(searchInput);

        if (personsByName.isEmpty() && personsByPhoneNumber.isEmpty()) {
            return null;
        }else if (!personsByPhoneNumber.isEmpty()) {
            return personsByPhoneNumber;
        }else{
            return personsByName;
        }
    }
}
