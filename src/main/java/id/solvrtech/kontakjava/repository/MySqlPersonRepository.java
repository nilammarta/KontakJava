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

        try {
            conn = mySqlconnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                Person person = new Person(id, name, phoneNumber);
                persons.add(person);
            }

            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Person getById(int personId) {
        String query = "SELECT * FROM persons WHERE id = ?";

        try {
            conn = mySqlconnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, personId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                Person person = new Person(personId, name, phoneNumber);

                return person;
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Person> getByName(String name) {
        String query = "SELECT * FROM persons WHERE name LIKE ?";

        try{
            conn = mySqlconnection.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();

            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String theName = rs.getString("name");
                String phoneNumber = rs.getString("phone");

                Person person = new Person(id, theName, phoneNumber);
                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Person> getByPhone(String phone) {
        String query = "SELECT * FROM persons WHERE phone LIKE ?";

        try{
            conn = mySqlconnection.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + phone + "%");
            rs = stmt.executeQuery();

            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String theName = rs.getString("name");
                String phoneNumber = rs.getString("phone");

                Person person = new Person(id, theName, phoneNumber);

                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Person create(Person person) {
        String query = "INSERT INTO persons (name, phone) VALUES (?, ?)";

        try {
            conn =mySqlconnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getPhoneNumber());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                person.setId(id);
                return person;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Person update(Person person) {
        String query = "UPDATE persons SET name = ?, phone = ? WHERE id = ?";

        try{
            conn = mySqlconnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getPhoneNumber());
            stmt.setInt(3, person.getId());
            stmt.executeUpdate();
            return person;

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally{
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM persons WHERE id = ?";

        try{
            conn = mySqlconnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        String query = "SELECT * FROM persons WHERE phone = ?";

        try {
            conn = mySqlconnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, phoneNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return true;
        }finally {
            if (conn != null) {
                try {
                    // close connection
                    mySqlconnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isPhoneNumberExists(String phoneNumber, int id) {
        String query = "SELECT * FROM persons WHERE phone = ? AND id != ?";

        try{
            conn = mySqlconnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, phoneNumber);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return true;
        }finally {
            try {
                // close connection
                mySqlconnection.closeConnection(stmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
