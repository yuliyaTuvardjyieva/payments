package by.ittc.payments;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.ittc.payments.controller.command.CommandException;
import by.ittc.payments.controller.command.CommandFactory;
import by.ittc.payments.controller.command.ICommand;
import by.ittc.payments.db.DaoException;


public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String page = request.getParameter("page");
		ICommand command = CommandFactory.getPageCommand(page);
		if (command != null){
			
				try {
					command.execute(request, response);
				} catch (CommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	
		}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		ICommand command = CommandFactory.getActionCommand(action);

		if (command != null) {
				try {
					command.execute(request, response);
				} catch (CommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

    public MainServlet() {
        super();
    }

}
