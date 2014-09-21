package by.ittc.payments.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.ittc.payments.db.DaoException;
import by.ittc.payments.db.ICardDAO;
import by.ittc.payments.model.CreditCard;

public class CardDAO implements ICardDAO {
    public static final String UPDATE_CARD_BY_ID = "UPDATE cards SET counts_id = ?, active_status = ? WHERE id = ?";


    private DataSource dataSource;


    @Override
    public void save(CreditCard card) throws DaoException {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(CreditCard card) throws DaoException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            int countId = card.getCount().getCountID();
            int cardId = card.getCardID();
            int cardStatus = 0;
            if (card.getStatus()) {
                cardStatus = 1;
            }
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(UPDATE_CARD_BY_ID);
            ps.setInt(1, countId);
            ps.setInt(2, cardStatus);
            ps.setInt(3, cardId);
            ps.execute();
        } catch (SQLException e) {
            throw new DaoException(ps.toString()); // TODO: Add discription of
                                                   // Exception
        } finally {
            closeConection(connection, rs, ps);
        }
    }

    @Override
    public void remove(CreditCard card) throws DaoException {
        // TODO Auto-generated method stub
    }

    @Override
    public CreditCard get(int id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void closeConection(Connection connection, ResultSet resultSetPerson, PreparedStatement preparedStatement) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // NON
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            // NON
        }
        try {
            if (resultSetPerson != null) {
                resultSetPerson.close();
            }
        } catch (SQLException e) {
            // NON
        }
    }
}
