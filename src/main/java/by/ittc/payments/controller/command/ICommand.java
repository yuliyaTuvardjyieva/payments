package by.ittc.payments.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ittc.payments.db.DaoException;


public interface ICommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, DaoException, ServletException, IOException;
    // TODO: Make own exception

}
