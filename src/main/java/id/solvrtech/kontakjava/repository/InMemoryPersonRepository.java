package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * class implementation of person repository, and storing the persons data in ArrayList
 */
public class InMemoryPersonRepository implements PersonRepository {
    // create the variable with type ArrayList of persons
    private final ArrayList<Person> persons = new ArrayList<>();


    // implement all abstract method on interface
    public ArrayList<Person> getAll() {
        return persons;
    }

    public Person getById(int id) {

        // Menggunakan method stream dengan filter() untuk mendapatklan obejct pertama yang cocok dengan id.
        // dan akan mengembalikan data person yang ditemukan pertama atau null jika tidak ditemukan datanya
        return persons.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    public ArrayList<Person> getByName(String name) {
        return persons.stream()
                .filter(person -> person.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    public ArrayList<Person> getByPhone(String phone) {
        return persons.stream()
                .filter(person -> person.getPhoneNumber().toLowerCase().contains(phone.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Person create(Person person) {
        if (persons.size() == 0) {
            person.setId(1);
        } else {
            person.setId(persons.getLast().getId() + 1);
        }

        persons.add(person);
        return person;
    }

    public Person update(Person person) {
        Person personEdit = getById(person.getId());
        int index = persons.indexOf(personEdit);
        persons.set(index, person);
        return person;
    }

    public void deleteById(int id) {
        persons.removeIf(person -> person.getId() == id);
    }
}
