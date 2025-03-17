package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.utils.MysqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class MySqlPersonRepository extends BaseRepository<Person> implements PersonRepository {

    public MySqlPersonRepository() {
        super(new MysqlConnection());
    }


    public List<Person> getAll() {
        return this.executeQueryForMultipleData("SELECT * FROM persons", null);
    }

    public Person getById(int personId) {
        return this.executeQueryForSingleData(
                "SELECT * FROM persons WHERE id = ?",
                stmt -> stmt.setInt(1, personId));
    }

    public List<Person> getByName(String name) {
        return this.executeQueryForMultipleData(
                "SELECT * FROM persons WHERE name LIKE ?",
                stmt -> stmt.setString(1, "%" + name + "%")
        );

    }

    public List<Person> getByPhone(String phone) {
        return this.executeQueryForMultipleData(
                "SELECT * FROM persons WHERE phone LIKE ?",
                stmt -> stmt.setString(1, "%" + phone + "%")
        );

    }

    public Person create(Person person) {
        int id = this.executeCreate(
                "INSERT INTO persons (name, phone) VALUES (?, ?)",
                stmt -> {
                    stmt.setString(1, person.getName());
                    stmt.setString(2, person.getPhoneNumber());
                }
        );
        return getById(id);

    }

    public Person update(Person person) {
        this.executeUpdate(
                "UPDATE persons SET name = ?, phone = ? WHERE id = ?",
                stmt -> {
                    stmt.setString(1, person.getName());
                    stmt.setString(2, person.getPhoneNumber());
                    stmt.setInt(3, person.getId());
                }
        );
        return getById(person.getId());
    }

    public void deleteById(int id) {
        this.executeDelete(
                "DELETE FROM persons WHERE id = ?",
                stmt -> stmt.setInt(1, id)
        );

    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        final int[] result = {0};

        this.executeQuery(
                "SELECT COUNT(*) FROM persons WHERE phone = ?",
                stmt -> stmt.setString(1, phoneNumber),
                resultSet -> {
                    if (resultSet.next()) {
                        result[0] = resultSet.getInt(1);
                    }
                }
        );

        if (result[0] == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isPhoneNumberExists(String phoneNumber, int id) {
        final int[] result = {0};

        this.executeQuery(
                "SELECT COUNT(*) FROM persons WHERE phone = ? AND id != ?",
                stmt -> {
                    stmt.setString(1, phoneNumber);
                    stmt.setInt(2, id);
                },
                resultSet -> {
                    if (resultSet.next()) {
                        result[0] = resultSet.getInt(1);
                    }
                }
        );

        if (result[0] == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<Person> getByNameOrPhone(String search) {
        return this.executeQueryForMultipleData(
                "SELECT * FROM persons WHERE LOWER(name) like ? OR phone like ?",
                stmt -> {
                    stmt.setString(1, "%" + search.toLowerCase() + "%");
                    stmt.setString(2, "%" + search + "%");
                }
        );
    }

    @Override
    protected Person mapToEntity(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                return new Person(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
