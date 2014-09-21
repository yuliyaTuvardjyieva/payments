package by.ittc.payments.model.persons;

import java.util.ArrayList;
import java.util.List;

import by.ittc.payments.model.CreditCard;

public class Client extends AbstractPerson {
    private static final long serialVersionUID = -8764139244654941646L;

    private List<CreditCard> creditCards = new ArrayList<CreditCard>();

    public Client() {
        super();
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void addCreditCards(CreditCard card) {
        this.creditCards.add(card);
    }

    public Object getInformation(Object o) {
        return null;
    }

    public Object makeRequest(Object o) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
