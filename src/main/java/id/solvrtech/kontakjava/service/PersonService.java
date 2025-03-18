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

    /**
     * Get all data from database
     * @return List of Person
     */
    public List<Person> getAll() {
        return personRepository.getAll();
    }


    /**
     * Get the person data based on their id
     * @param id
     * @return data with type Person
     */
    public Person getById(int id) {
        return personRepository.getById(id);
    }

    /**
     * Create new person data and save into databse
     * @param name
     * @param phoneNumber
     * @return data with type Person
     */
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

    /**
     *
     * @param personEdit
     * @param newName
     * @param newPhoneNumber
     * @return data with type Person
     */
    public Person update(Person personEdit, String newName, String newPhoneNumber) {

        if (personRepository.isPhoneNumberExists(newPhoneNumber, personEdit.getId())) {
            return null;
        }
        personEdit.setName(newName);
        personEdit.setPhoneNumber(newPhoneNumber);

        return personRepository.update(personEdit);
    }

    /**
     * Remove data in database based on their ID
     * @param id
     * @return void
     */
    public String delete(int id) {
        try {
            personRepository.deleteById(id);
            return "Person data deleted successfully!";
        }catch (Exception e) {
            return "Person data could not be deleted!";
        }
    }

    /**
     * Search person data by their name or phone number
     * @param searchInput
     * @return List of Person data that matching with search input
     */
    public List<Person> searchPerson(String searchInput) {

        if (searchInput.length() > 3 && searchInput.substring(0, 3).equals("+62")) {
            searchInput = "0" + searchInput.substring(4);
        }

        return personRepository.getByNameOrPhone(searchInput);
    }
}
