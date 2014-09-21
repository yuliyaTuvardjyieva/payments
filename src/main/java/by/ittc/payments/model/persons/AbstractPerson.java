package by.ittc.payments.model.persons;

import java.io.Serializable;

public abstract class AbstractPerson implements Serializable {
    private static final long serialVersionUID = 5108382401202384530L;

    private int id;
    protected String firstName;
    protected String lastName;
    protected String login;
    protected String password;

    public AbstractPerson() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;
        else
            return (((AbstractPerson) obj).id == this.id) ? true : false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
