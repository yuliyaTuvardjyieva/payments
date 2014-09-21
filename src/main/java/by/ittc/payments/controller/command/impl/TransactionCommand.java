package by.ittc.payments.controller.command.impl;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import by.ittc.payments.controller.command.CommandException;
import by.ittc.payments.controller.command.CommandFactory;
import by.ittc.payments.controller.command.ICommand;
import by.ittc.payments.db.DAOFacade;
import by.ittc.payments.db.DaoException;
import by.ittc.payments.db.ICountDAO;

//
//	public boolean validate(int summaTransaction, int countReceiverId,
//			int countID) throws DaoException {
//
//		ICountDAO countDAO = DAOFacade.getCountDAO();
//
//		if ((((!(countReceiverId <= 0)) && (!(summaTransaction <= 0))) &&
//
//		(countDAO.checkCountExist(countReceiverId) == true) && (countDAO
//				.selectValue(countID) >= summaTransaction))) {
//
//			return true;
//		} else {
//			return false;
//		}
//
//	}


import by.ittc.payments.model.Count;
import by.ittc.payments.model.CreditCard;
import by.ittc.payments.model.persons.Client;

public class TransactionCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        int countReceiverId = Integer.parseInt(request.getParameter("count_number2"));
        float summaTransaction = Float.parseFloat(request.getParameter("summa"));
        int countID = Integer.parseInt(request.getParameter("count_number"));
        Count countFrom = null;
        Count countTo = null;
        Client client = (Client) request.getSession().getAttribute("person");
        List<CreditCard> cardList = client.getCreditCards();
        for (CreditCard card : cardList) {
            if (card.getCount().getCountID() == countID) {
                countFrom = card.getCount();
            }
        }
        ICountDAO countDAO = DAOFacade.getCountDAO();
        try {
            countTo = countDAO.get(countReceiverId);
            if (validate(countFrom, countTo, summaTransaction) == false) {
                throw new CommandException("No money");
            }
            countTo.setValue(countTo.getValue() + summaTransaction);
            countDAO.update(countTo);
            countFrom.setValue(countFrom.getValue() - summaTransaction);
            countDAO.update(countFrom);
            CommandFactory.getPageCommand("transaction").execute(request, response);
        } catch (DaoException e) {
            throw new CommandException("Cant update", e);
        }
    }

    public boolean validate(Count from, Count to, float sum) {
        if (from.getValue() < sum) {
            return false;
        } else {
            return true;
        }

    }

}
