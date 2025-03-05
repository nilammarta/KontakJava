package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import static id.solvrtech.kontakjava.utils.Helper.covertResultSetToList;
import static id.solvrtech.kontakjava.utils.MysqlConnection.closeConnection;
import static id.solvrtech.kontakjava.utils.MysqlConnection.getConnection;

//public class MySqlPersonRepository implements PersonRepository {

    // Menyiapkan objek yang diperlukan untuk mengelola database
//    Connection conn;
//    Statement stmt;
//    ResultSet rs;
//
//    public ArrayList<Person> getAll() {
//        try {
//            conn = getConnection();
//            assert conn != null;
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM persons");
//            return covertResultSetToList(rs);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            // tutup koneksi
//            if (conn != null) {
//                try {
//                    closeConnection(stmt, conn);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public Person getById(int personId) {
//        try {
//            conn = getConnection();
//            assert conn != null;
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM persons WHERE id = personId");
//            rs.first();
//            return new Person(rs.getString("name"), rs.getString("phone_number"));
//        }catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public ArrayList<Person> getByName(String name) {
//        return null;
//    }
//
//    public ArrayList<Person> getByPhone(String phone) {
//        return null;
//    }
//
//    public Person create(Person person) {
//        try {
//            conn = getConnection();
//            assert conn != null;
//            stmt = conn.createStatement();
//            stmt.executeUpdate("INSERT INTO persons (name, phone) VALUES ('" + person.getName() + "', '" + person.getPhoneNumber() + "')");
//            closeConnection(stmt, conn);
//            return person;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            // tutup koneksi
//            if (conn != null) {
//                try {
//                    closeConnection(stmt, conn);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public Person update(Person person) {
//        return null;
//    }
//
//    public void deleteById(int id) {
//
//    }
//}
