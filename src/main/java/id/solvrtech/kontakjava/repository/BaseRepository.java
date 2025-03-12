package id.solvrtech.kontakjava.repository;

import id.solvrtech.kontakjava.model.Person;
import id.solvrtech.kontakjava.utils.MysqlConnection;

import java.beans.PropertyEditorSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T> {

    protected MysqlConnection mysqlConnection;

    // Connection of database
    public BaseRepository(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    // Maps the ResultSet into T (Person)
    protected abstract T mapToEntity(ResultSet resultSet);


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

    protected int executeCreate(String query, PreparedStatementSetter setter) {

        return 0;
    }

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
}
