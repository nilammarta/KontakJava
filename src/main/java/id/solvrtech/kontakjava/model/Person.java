package id.solvrtech.kontakjava.model;

/**
 * Data model for the person contact
 */
public class Person {
    // id
    int id;
    // name
    String name;
    // phone number
    int phoneNumber;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
