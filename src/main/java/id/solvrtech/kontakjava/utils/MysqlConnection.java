package id.solvrtech.kontakjava.utils;

import java.sql.*;

public class MysqlConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:33061/kontakjavadb";
    private static final String USER = "root";
    private static final String PASS = "password";
//    private static final Connection CONNECTION = createConnection();

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

    public void closeConnection(Statement statement, Connection connection) throws SQLException {
        statement.close();
        connection.close();
    }
}
