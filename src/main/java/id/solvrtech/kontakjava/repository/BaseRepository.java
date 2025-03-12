package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.utils.MysqlConnection;

import java.beans.PropertyEditorSupport;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T> {

    // or any database connection wrapper class that you use.
    protected MysqlConnection mysqlConnection;

    // Connection of database
    public BaseRepository(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }


    /**
     * Maps the given ResultSet into T
     * @param resultSet {@link ResultSet}
     * @return T
     */
    protected abstract T mapToEntity(ResultSet resultSet);

    /**
     * Executes a general SQL query, with optional PreparedStatement setter for binding any query value, and optional
     * ResultSetAction for performing any action / codes which will be taken once the query has been done.
     * @param query  String
     * @param setter {@link PreparedStatementSetter}
     */

    protected void executeQuery(String query, PreparedStatementSetter setter, ResultSetAction action) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = mysqlConnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            if (setter != null) {
                setter.setValues(stmt);
            }
            ResultSet rs = stmt.executeQuery();
            action.perform(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    // close connection
                    mysqlConnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Executes an SQL query for getting a single data (converted into T), with optional PreparedStatement setter for
     * binding any query value.
     * @param query  String
     * @param setter {@link PreparedStatementSetter}
     * @return T
     */

    protected T executeQueryForSingleData(String query, PreparedStatementSetter setter) {
        List<T> entity = new ArrayList<>();
//        final T[] entity = new Object[]{new T()};
        executeQuery(
                query,
                setter,
                new ResultSetAction() {
                    @Override
                    public void perform(ResultSet resultSet) throws SQLException {
                        if (resultSet.next()) {
                            entity.add(mapToEntity(resultSet));
                        }
                    }
                }
        );
        return entity.getFirst();
    }

    /**
     * Executes a SQL query for getting list of data (converted into List of T), with optional PreparedStatement setter
     * for binding any query value.
     * @param query  String
     * @param setter {@link PreparedStatementSetter}
     * @return list of T
     */

    protected List<T> executeQueryForMultipleData(String query, PreparedStatementSetter setter) {
        List<T> data = new ArrayList<>();

        executeQuery(
                query,
                setter,
                new ResultSetAction() {
                    public void perform(ResultSet resultSet) throws SQLException {
                        while (resultSet.next()) {
                            data.add(mapToEntity(resultSet));
                        }
                    }
                }
        );

        return data;
    }

    /**
     * Executes an SQL "insert" query with the given query and the optional PreparedStatement setter for binding any
     * query value.
     * @param query  String
     * @param setter {@link PreparedStatementSetter}
     * @return last generated ID of the SQL insert query
     */

    protected int executeCreate(String query, PreparedStatementSetter setter) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = mysqlConnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (setter != null) {
                setter.setValues(stmt);
            }
            return stmt.executeUpdate();
//            return stmt.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (conn != null) {
                try {
                    // close connection
                    mysqlConnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Executes an SQL "update" query with the given query and the optional PreparedStatement setter for binding any
     * query value.
     * @param query  String
     * @param setter {@link PreparedStatementSetter}
     */

    protected void executeUpdate(String query, PreparedStatementSetter setter) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = mysqlConnection.createConnection();
            assert conn != null;
            stmt = conn.prepareStatement(query);
            if (setter != null) {
                setter.setValues(stmt);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    // close connection
                    mysqlConnection.closeConnection(stmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Executes an SQL "delete" query with the given query and the optional PreparedStatement setter for binding any
     * query value.
     * @param query  String
     * @param setter {@link PreparedStatementSetter}
     */
    protected void executeDelete(String query, PreparedStatementSetter setter) {
        this.executeUpdate(query, setter);
    }

}
