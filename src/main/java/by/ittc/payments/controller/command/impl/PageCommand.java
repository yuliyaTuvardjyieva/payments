package by.ittc.payments.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ittc.payments.controller.command.CommandFactory;
import by.ittc.payments.controller.command.ICommand;

import by.ittc.payments.db.DaoException;

import by.ittc.payments.controller.command.CommandException;

import by.ittc.payments.model.persons.Administrator;
import by.ittc.payments.model.persons.Client;

public class PageCommand implements ICommand {

    private String page;

    public PageCommand(String page) {
        this.page = page;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DaoException, ServletException, IOException {
        if ("autorization".equals(page) && request.getSession().getAttribute("person") != null) {
            Object person = request.getSession().getAttribute("person");
            if (person instanceof Administrator) {
                CommandFactory.getPageCommand("adminPanel").execute(request, response);
                return;
            }
            if (person instanceof Client) {
                CommandFactory.getPageCommand("clientPanel").execute(request, response);
                return;
            }
        }
        try {
            if ("index".equals(page)) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                StringBuilder pageBuilder = new StringBuilder("pages/");
                pageBuilder.append(page);
                pageBuilder.append(".jsp");
                request.getRequestDispatcher(pageBuilder.toString()).forward(request, response);
            }
        } catch (ServletException | IOException e) {
            throw new CommandException("Cant execute this page", e);
        }
    }

}
