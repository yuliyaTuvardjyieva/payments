package by.ittc.payments.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.ittc.payments.db.DaoException;
import by.ittc.payments.db.IPersonDAO;
import by.ittc.payments.model.Count;
import by.ittc.payments.model.CreditCard;
import by.ittc.payments.model.persons.AbstractPerson;
import by.ittc.payments.model.persons.Administrator;
import by.ittc.payments.model.persons.Client;

public class PersonDAO implements IPersonDAO {

    private static final String SELECT_FROM_PERSONS_BY_LOGIN_PASSWORD;
    static {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append("persons_id, ");
        builder.append("login, ");
        builder.append("password, ");
        builder.append("last_name, ");
        builder.append("access_level, ");
        builder.append("first_name, ");
        builder.append("cards.id AS cards_id, ");
        builder.append("counts_id, ");
        builder.append("active_status, ");
        builder.append("persons_id, ");
        builder.append("value ");
        builder.append("FROM persons ");
        builder.append("LEFT JOIN cards ON persons.id = cards.persons_id ");
        builder.append("LEFT JOIN counts ON counts.id = cards.counts_id ");
        builder.append("WHERE login = ? and password = ?");
        SELECT_FROM_PERSONS_BY_LOGIN_PASSWORD = builder.toString();
    }

    private static final String SELECT_FROM_PERSONS_BY_LOGIN = "SELECT * FROM persons WHERE login = ?";
    private static final String INSERT_INTO_PERSONS_CLIENTS = "INSERT INTO persons (login, password, last_name,access_level,first_name) VALUES (?,?,?,2,?)";

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void update(AbstractPerson person) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(AbstractPerson person) {
        // TODO Auto-generated method stub
    }

    @Override
    public AbstractPerson get(String login, String password) throws DaoException {
        AbstractPerson person = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(SELECT_FROM_PERSONS_BY_LOGIN_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                if ("ADMIN".equals(rs.getString("access_level"))) {
                    return extractAdmin(rs);
                }
                if ("CLIENT".equals(rs.getString("access_level"))) {
                    return extractClient(rs);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(); // TODO: Add discription of Exception
        } finally {
            closeConection(connection, rs, ps);
        }
        return person;
    }

    public boolean checkLogin(String login) throws DaoException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(SELECT_FROM_PERSONS_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(); // TODO: Add discription of Exception
        } finally {
            closeConection(connection, rs, ps);
        }
    }

    @Override
    public void save(AbstractPerson person) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_INTO_PERSONS_CLIENTS);
            preparedStatement.setString(1, person.getLogin());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getFirstName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("database exception, cant save person in database", e);
        } finally {
            closeConection(connection, null, preparedStatement);
        }
    }

    private void closeConection(Connection connection, ResultSet resultSetPerson, PreparedStatement preparedStatement) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e2) {
            // NON
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e2) {
            // NON
        }
        try {
            if (resultSetPerson != null) {
                resultSetPerson.close();
            }
        } catch (SQLException e2) {
            // NON
        }
    }

    private Administrator extractAdmin(ResultSet rs) throws SQLException {
        Administrator admin = new Administrator();
        admin.setFirstName(rs.getString("first_name"));
        admin.setLastName(rs.getString("last_name"));
        admin.setLogin(rs.getString("login"));
        admin.setPassword(rs.getString("password"));
        admin.setId(rs.getInt("persons_id"));
        return admin;
    }

    private Client extractClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("persons_id"));
        client.setFirstName(rs.getString("first_name"));
        client.setLastName(rs.getString("last_name"));
        client.setLogin(rs.getString("login"));
        client.setPassword(rs.getString("password"));
        do {
            client.addCreditCards(extractCard(rs));
        } while (rs.next());
        return client;
    }

    private CreditCard extractCard(ResultSet rs) throws SQLException {
        CreditCard card = new CreditCard();
        card.setCardID(rs.getInt("cards_id"));
        card.setStatus(rs.getBoolean("active_status"));
        card.setCount(extractCount(rs));
        return card;
    }

    private Count extractCount(ResultSet rs) throws SQLException {
        Count count = new Count();
        count.setCountID(rs.getInt("counts_id"));
        count.setValue(rs.getInt("value"));
        return count;
    }

}
