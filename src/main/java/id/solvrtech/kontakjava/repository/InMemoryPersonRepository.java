package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * class implementation of person repository, and storing the persons data in ArrayList
 */
public class InMemoryPersonRepository implements PersonRepository {
    // create the variable with type List of persons
    private final List<Person> persons = new ArrayList<Person>();


    // implement all abstract method on interface
    public List<Person> getAll() {
        return persons;
    }

    public Person getById(int id) {

        // Menggunakan method stream dengan filter() untuk mendapatklan obejct pertama yang cocok dengan id.
        // dan akan mengembalikan data person yang ditemukan pertama atau null jika tidak ditemukan datanya
        return persons.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    public List<Person> getByName(String name) {
        return persons.stream()
                .filter(person -> person.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    public List<Person> getByPhone(String phone) {
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

    public boolean isPhoneNumberExists(String phoneNumber) {
        for (Person person : persons) {
            if (Objects.equals(person.getPhoneNumber(), phoneNumber)) {
                return true;
            }
        }
        return false;
    }


    public boolean isPhoneNumberExists(String phoneNumber, int id) {
        for (Person person : persons) {
            if (Objects.equals(person.getPhoneNumber(), phoneNumber) && !Objects.equals(person.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
