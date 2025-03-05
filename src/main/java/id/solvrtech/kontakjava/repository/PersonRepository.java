package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * interface for persons repository
 */
public interface PersonRepository {
    List<Person> getAll();

    Person getById(int id);

    List<Person> getByName(String name);

    List<Person> getByPhone(String phone);

    Person create(Person person);

    Person update(Person person);

    void deleteById(int id);

    boolean isPhoneNumberExists(String phoneNumber);

    // overloading isPhoneNumberExists untuk bagian edit person
    boolean isPhoneNumberExists(String phoneNumber, int id);
}
