package by.ittc.payments.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ittc.payments.controller.command.CommandFactory;
import by.ittc.payments.controller.command.ICommand;
import by.ittc.payments.controller.command.CommandException;
import by.ittc.payments.db.DAOFacade;
import by.ittc.payments.db.DaoException;
import by.ittc.payments.db.IPersonDAO;
import by.ittc.payments.model.persons.Client;

public class RegistrationCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String passwordConf = request.getParameter("passwordConf");
        try {
            if (validate(login, password, lastName, passwordConf, firstName) == true) {
                IPersonDAO personDAO = DAOFacade.getPersonDAO();
                Client client = new Client();
                client.setLogin(login);
                client.setPassword(password);
                client.setLastName(lastName);
                client.setFirstName(firstName);
                personDAO.save(client);
                CommandFactory.getPageCommand("autorization").execute(request, response);
            } else {
                request.getSession().setAttribute("loginError", "error");
                CommandFactory.getPageCommand("registration").execute(request, response);
            }
        } catch (DaoException e) {
            throw new CommandException("cant registr such person", e);
        }
    }

    public boolean validate(String login, String password, String lastName, String passwordConf, String firstName)
            throws DaoException {

        IPersonDAO personDAO = DAOFacade.getPersonDAO();
        if (((!(login == "")) && (!(password == "")) && (!(lastName == "")) && (!(passwordConf == "")) && (!(firstName == "")))
                && (password.equals(passwordConf))) {
            if (personDAO.checkLogin(login) == true) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

}
