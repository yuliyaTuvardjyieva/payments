package by.ittc.payments.db;

import javax.sql.DataSource;

import by.ittc.payments.model.CreditCard;

public interface ICardDAO {

    void save(CreditCard card) throws DaoException;

    void update(CreditCard card) throws DaoException;

    void remove(CreditCard card) throws DaoException;

    CreditCard get(int id) throws DaoException;

    void setDataSource(DataSource dataSource);

}
