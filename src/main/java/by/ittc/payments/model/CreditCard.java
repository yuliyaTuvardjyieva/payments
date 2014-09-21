package by.ittc.payments.model;

import java.io.Serializable;

public class CreditCard implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cardID;
    private Count count = new Count();
    private boolean status;

    public CreditCard() {
        super();
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

}
