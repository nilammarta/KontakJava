package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;

import java.util.ArrayList;

/**
 * interface for persons repository
 */
public interface PersonRepository {
    ArrayList<Person> getAll();

    Person getById(int id);

    ArrayList<Person> getByName(String name);

    ArrayList<Person> getByPhone(String phone);

    Person create(Person person);

    Person update(Person person);

    void deleteById(int id);

}
