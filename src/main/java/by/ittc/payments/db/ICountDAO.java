package by.ittc.payments.db;



import javax.sql.DataSource;

import by.ittc.payments.model.Count;

public interface ICountDAO {

    void save(Count count) throws DaoException;

	//void update(Count card) throws DaoException;

    void remove(Count card) throws DaoException;


    Count get(int id) throws DaoException;

    void setDataSource(DataSource dataSource);


	Count update(int countID, int countReceiverId, int summaTransaction)
			throws DaoException;

	boolean checkCountExist(int countReceiverId)throws DaoException;
	int selectValue(int countID) throws DaoException;


    void update(Count count) throws DaoException;

}
