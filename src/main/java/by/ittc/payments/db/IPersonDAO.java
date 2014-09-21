package by.ittc.payments.db;

import javax.sql.DataSource;

import by.ittc.payments.model.persons.AbstractPerson;

public interface IPersonDAO {

    void save(AbstractPerson person) throws DaoException;

    void update(AbstractPerson person) throws DaoException;

    void remove(AbstractPerson person) throws DaoException;

    AbstractPerson get(String login, String password) throws DaoException;

    void setDataSource(DataSource dataSource);

    boolean checkLogin(String login) throws DaoException;

}
