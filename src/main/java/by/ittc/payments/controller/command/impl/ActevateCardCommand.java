package by.ittc.payments.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ittc.payments.controller.command.CommandFactory;
import by.ittc.payments.controller.command.ICommand;
import by.ittc.payments.controller.command.CommandException;
import by.ittc.payments.db.DAOFacade;
import by.ittc.payments.db.DaoException;
import by.ittc.payments.db.ICardDAO;
import by.ittc.payments.model.CreditCard;
import by.ittc.payments.model.persons.Client;

public class ActevateCardCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DaoException, ServletException, IOException {
        Client client = (Client) request.getSession().getAttribute("person");
        List<CreditCard> cardList = client.getCreditCards();
        int cardId = Integer.parseInt(request.getParameter("cardId"));
        CreditCard card = null;
        for (CreditCard c : cardList) {
            if (c.getCardID() == cardId) {
                card = c;
                break;
            }
        }
        ICardDAO cardDAO = DAOFacade.getCardDAO();
        card.setStatus(true);
        try {
            cardDAO.update(card);
        } catch (DaoException e) {
            throw new CommandException("Can`t activate card", e);
        }
        CommandFactory.getPageCommand("cardStatus").execute(request, response);
    }
}
