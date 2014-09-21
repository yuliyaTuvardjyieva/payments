package by.ittc.payments.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ittc.payments.controller.command.CommandException;
import by.ittc.payments.controller.command.CommandFactory;
import by.ittc.payments.controller.command.ICommand;

import by.ittc.payments.db.DaoException;

public class LogoutCommand implements ICommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DaoException, CommandException {
		
		request.getSession().setAttribute("person", null);
		CommandFactory.getPageCommand("index").execute(request, response);
		
	}
}