package by.ittc.payments.model;

import java.io.Serializable;

public class Count implements Serializable {



	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Count(int countID, int summaTransaction, int countReceiverId) {
		super();
		this.countID = countID;
		this.summaTransaction = summaTransaction;
		this.countReceiverId = countReceiverId;
	}

	private int summaTransaction;
	private int countReceiverId;
	


    private int countID;
    private float value;
    private String currency;

    public Count() {
        super();
    }

    public int getCountID() {
        return countID;
    }

    public void setCountID(int countID) {
        this.countID = countID;
    }

    public float getValue() {
        return value;
    }





	public int getSummaTransaction() {
		return summaTransaction;
	}

	public void setSummaTransaction(int summaTransaction) {
		this.summaTransaction = summaTransaction;
	}

    public void setValue(float value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


	public int getCountReceiverId() {
		return countReceiverId;
	}

	public void setCountReceiverId(int countReceiverId) {
		this.countReceiverId = countReceiverId;
	}



}
