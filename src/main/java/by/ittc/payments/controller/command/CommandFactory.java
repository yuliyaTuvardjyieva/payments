package by.ittc.payments.controller.command;


import by.ittc.payments.controller.command.impl.ActevateCardCommand;
import by.ittc.payments.controller.command.impl.DeactevateCardCommand;
import by.ittc.payments.controller.command.impl.LoginCommand;
import by.ittc.payments.controller.command.impl.LogoutCommand;
import by.ittc.payments.controller.command.impl.PageCommand;
import by.ittc.payments.controller.command.impl.RegistrationCommand;
import by.ittc.payments.controller.command.impl.TransactionCommand;

public abstract class CommandFactory {

    public static ICommand getPageCommand(String page) {
        if (page == null) {
            return null;
        } else {
            return new PageCommand(page);
        }
    }

    public static ICommand getActionCommand(String name) {
        switch (name) {
        case "login":
            return new LoginCommand();
        case "logout":
            return new LogoutCommand();
        case "registration":
            return new RegistrationCommand();
        case "activate":
            return new ActevateCardCommand();
        case "diactivate":
            return new DeactevateCardCommand();
        case "transaction":
            return new TransactionCommand();
        default:
            System.out.println("No that comand:  " + name);
            return null;
        }
    }

}