package by.ittc.payments.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ittc.payments.controller.command.CommandException;
import by.ittc.payments.controller.command.CommandFactory;
import by.ittc.payments.controller.command.ICommand;
//import by.ittc.payments.controller.command.CommandException;
import by.ittc.payments.db.DAOFacade;
import by.ittc.payments.db.DaoException;
import by.ittc.payments.db.IPersonDAO;
import by.ittc.payments.model.persons.AbstractPerson;
import by.ittc.payments.model.persons.Administrator;
import by.ittc.payments.model.persons.Client;

public class LoginCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws DaoException, CommandException, ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        IPersonDAO personDAO = DAOFacade.getPersonDAO();
        AbstractPerson person;
     //   try {
            person = personDAO.get(login, password);
//            if (person == null) {
//                throw new CommandException("No such person");
//            }
            if (person instanceof Administrator) {
                Administrator admin = (Administrator) person;
                request.getSession().setAttribute("person", admin);
                CommandFactory.getPageCommand("adminPanel").execute(request, response);
            } else if (person instanceof Client) {
                Client client = (Client) person;
                request.getSession().setAttribute("person", client);
                CommandFactory.getPageCommand("clientPanel").execute(request, response);
            } else {
                request.setAttribute("loginError", "error");
                CommandFactory.getPageCommand("autorization").execute(request, response);
            }
       } 
 //           catch (DaoException e) {
//            throw new CommandException("Can`t login", e);
//        }
    }

