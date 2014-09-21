package by.ittc.payments.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import by.ittc.payments.db.DaoException;
import by.ittc.payments.db.ICountDAO;
import by.ittc.payments.model.Count;

public class CountDAO implements ICountDAO {


//	private DataSource dataSource;
//
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	private static final String UPDATE_SUMMA_COUNT_RECEIVER = "UPDATE counts SET value = value - ?, summa_last_transaction = ?, count_receiver_id = ? WHERE id=? ";
//	private static final String UPDATE_VALUE_COUNT_RECEIVER = "UPDATE counts SET value = value + ? WHERE id=? ";
//	private static final String SELECT_FROM_COUNTS_COUNT_ID = "SELECT id FROM counts WHERE id=?";
//	private static final String SELECT_FROM_COUNTS_VALUE_COUNT = "SELECT value FROM counts WHERE id=?";
//
//	@Override
//	public void save(Count count) throws DaoException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Count update(int countID, int countReceiverId, int summaTransaction)
//			throws DaoException {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		Count count = null;
//		PreparedStatement preparedStatement2 = null;
//
//		try {
//			connection = dataSource.getConnection();
//			preparedStatement = connection
//					.prepareStatement(UPDATE_SUMMA_COUNT_RECEIVER);
//			preparedStatement.setInt(1, summaTransaction);
//			preparedStatement.setInt(2, summaTransaction);
//			preparedStatement.setInt(3, countReceiverId);
//			preparedStatement.setInt(4, countID);
//			preparedStatement.executeUpdate();
//			preparedStatement2 = connection
//					.prepareStatement(UPDATE_VALUE_COUNT_RECEIVER);
//			preparedStatement2.setInt(1, summaTransaction);
//			preparedStatement2.setInt(2, countReceiverId);
//			preparedStatement2.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new DaoException("database exception", e);
//
//		} finally {
//			closeConection(connection, null, preparedStatement);
//			closeConection(connection, null, preparedStatement2);
//		}
//		return count;
//
//	}
//
//	private void closeConection(Connection connection,
//			ResultSet resultSetPerson, PreparedStatement preparedStatement) {
//		try {
//			if (connection != null) {
//				connection.close();
//			}
//		} catch (SQLException e2) {
//			// NON
//		}
//		try {
//			if (preparedStatement != null) {
//				preparedStatement.close();
//			}
//		} catch (SQLException e2) {
//			// NON
//		}
//		try {
//			if (resultSetPerson != null) {
//				resultSetPerson.close();
//			}
//		} catch (SQLException e2) {
//			// NON
//		}
//	}
//
//	@Override
//	public void remove(Count card) throws DaoException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Count get(int id) throws DaoException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public boolean checkCountExist(int countReceiverId) throws DaoException {
//		Connection connection = null;
//		ResultSet rs = null;
//		PreparedStatement ps = null;
//		try {
//			connection = dataSource.getConnection();
//			ps = connection.prepareStatement(SELECT_FROM_COUNTS_COUNT_ID);
//			ps.setInt(1, countReceiverId);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (SQLException e) {
//			throw new DaoException();
//		} finally {
//			closeConection(connection, rs, ps);
//		}
//	}
//
//	public int selectValue(int countID) throws DaoException {
//		Connection connection = null;
//		ResultSet rs = null;
//		PreparedStatement ps = null;
//		int h = 0;
//		try {
//			connection = dataSource.getConnection();
//			ps = connection.prepareStatement(SELECT_FROM_COUNTS_VALUE_COUNT);
//			ps.setInt(1, countID);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				h = rs.getInt("value");
//				return h;
//			}
//
//		} catch (SQLException e) {
//			throw new DaoException(); // TODO: Add discription of Exception
//		} finally {
//			closeConection(connection, rs, ps);
//		}
//		return h;
//
//	}
//=======
//>>>>>>> bcd9cbed360190db83806df3c6f44fbc7a473d41

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static final String UPDATE_CARD_BY_ID = "UPDATE counts SET value = ? WHERE id = ?";
    public static final String SELECT_COUNT = "SELECT * FROM counts WHERE id = ?";

    @Override
    public void save(Count count) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Count count) throws DaoException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            int countId = count.getCountID();
            float value = count.getValue();
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(UPDATE_CARD_BY_ID);
            ps.setFloat(1, value);
            ps.setInt(2, countId);
            ps.execute();
        } catch (SQLException e) {
            throw new DaoException(ps.toString()); // TODO: Add discription of
                                                   // Exception
        } finally {
            closeConection(connection, rs, ps);
        }
    }

    private void closeConection(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {
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
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e2) {
            // NON
        }
    }

    @Override
    public void remove(Count card) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public Count get(int id) throws DaoException {
        Count count = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(SELECT_COUNT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = new Count();
                count.setValue(rs.getFloat("value"));
                count.setCountID(id);
            }
        } catch (SQLException e) {
            throw new DaoException("Cant get count", e);
        } finally {
            closeConection(connection, rs, ps);
        }
        return count;
    }

	@Override
	public Count update(int countID, int countReceiverId, int summaTransaction)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkCountExist(int countReceiverId) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int selectValue(int countID) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}
}
