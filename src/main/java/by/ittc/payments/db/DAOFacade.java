package by.ittc.payments.db;

import javax.activation.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.ittc.payments.db.IPersonDAO;

public class DAOFacade {
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        DAOFacade.dataSource = dataSource;
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            new String[] { "dao.xml" });

    public static IPersonDAO getPersonDAO() {
        return (IPersonDAO) applicationContext.getBean("personDAO");
    }

    public static ICardDAO getCardDAO() {
        return (ICardDAO) applicationContext.getBean("cardDAO");
    }

    public static ICountDAO getCountDAO() {
        return (ICountDAO) applicationContext.getBean("countDAO");
    }

}
