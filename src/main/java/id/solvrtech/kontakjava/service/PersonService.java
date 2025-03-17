package id.solvrtech.kontakjava.service;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.repository.MySqlPersonRepository;
import id.solvrtech.kontakjava.repository.PersonRepository;

import java.util.List;

/**
 * class person service to create CRUD logic of person data
 */
public class PersonService {

    // Dengan menyimpan data di array list
//    private PersonRepository personRepository = new InMemoryPersonRepository();

//     Dengan menyimpan data di database
    private final PersonRepository personRepository = new MySqlPersonRepository();

    public List<Person> getAll() {
        return personRepository.getAll();
    }

    // get the person daya based on their id
    public Person getById(int id) {
        return personRepository.getById(id);
    }

    // logic of create
    public Person create(String name, String phoneNumber) {

        if (phoneNumber.substring(0, 3).equals("+62")) {
            phoneNumber = "0" + phoneNumber.substring(4);
        }

        if (personRepository.isPhoneNumberExists(phoneNumber)) {
            return null;
        } else {
            Person newPerson = new Person(name, phoneNumber);
            newPerson.setName(name);
            newPerson.setPhoneNumber(phoneNumber);
            personRepository.create(newPerson);

            return newPerson;
        }
    }

    // logic of update
    public Person update(Person personEdit, String newName, String newPhoneNumber) {

        if (personRepository.isPhoneNumberExists(newPhoneNumber, personEdit.getId())) {
            return null;
        }
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

    public List<Person> searchPerson(String searchInput) {

        if (searchInput.length() > 3 && searchInput.substring(0, 3).equals("+62")) {
            searchInput = "0" + searchInput.substring(4);
        }

        return personRepository.getByNameOrPhone(searchInput);
    }
}
