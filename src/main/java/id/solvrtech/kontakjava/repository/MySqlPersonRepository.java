package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.utils.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySqlPersonRepository implements PersonRepository {

//     Menyiapkan objek yang diperlukan untuk mengelola database
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    MysqlConnection mySqlconnection = new MysqlConnection();


    public List<Person> getAll() {
        String query = "SELECT * FROM persons";
        conn = mySqlconnection.createConnection();
        try {
            assert conn != null;
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                Person person = new Person(name, phoneNumber);
                person.setId(id);
                persons.add(person);
            }
            mySqlconnection.closeConnection(stmt, conn);
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person getById(int personId) {
        String query = "SELECT * FROM persons WHERE id = ?";
        conn = mySqlconnection.createConnection();

        try {
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, personId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                Person person = new Person(name, phoneNumber);
                person.setId(personId);
                // close connection
                mySqlconnection.closeConnection(stmt, conn);
                return person;
            }else{
                return null;
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Person> getByName(String name) {
        String query = "SELECT * FROM persons WHERE name LIKE ?";
        conn = mySqlconnection.createConnection();

        try{
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();

            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String theName = rs.getString("name");
                String phoneNumber = rs.getString("phone");

                Person person = new Person(theName, phoneNumber);
                person.setId(id);

                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Person> getByPhone(String phone) {
        String query = "SELECT * FROM persons WHERE phone LIKE ?";
        conn = mySqlconnection.createConnection();

        try{
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + phone + "%");
            rs = stmt.executeQuery();

            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String theName = rs.getString("name");
                String phoneNumber = rs.getString("phone");

                Person person = new Person(theName, phoneNumber);
                person.setId(id);

                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person create(Person person) {
        String query = "INSERT INTO persons (name, phone) VALUES (?, ?)";
        conn =mySqlconnection.createConnection();

        try {
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getPhoneNumber());
            stmt.executeUpdate();

            // close connection
            mySqlconnection.closeConnection(stmt, conn);
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person update(Person person) {
        String query = "UPDATE persons SET name = ?, phone = ? WHERE id = ?";
        conn = mySqlconnection.createConnection();

        try{
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getPhoneNumber());
            stmt.setInt(3, person.getId());
            stmt.executeUpdate();

            // close connection
            mySqlconnection.closeConnection(stmt, conn);
            return person;

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM persons WHERE id = ?";
        conn = mySqlconnection.createConnection();


    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        return false;
    }

    public boolean isPhoneNumberExists(String phoneNumber, int id) {
        return false;
        // SELECT * FROM persons WHERE phone LIKE ? AND id != ?
    }
}
