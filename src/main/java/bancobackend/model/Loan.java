package bancobackend.model;


import javax.persistence.*;

public class Loan {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLoan;

    private int valueLoan;

    private String state;

    private String idClient;



    public Loan(){

    }

    public int getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(int idLoan) {
        this.idLoan = idLoan;
    }

    public int getValueLoan() {
        return valueLoan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setValueLoan(int valueLoan) {
        this.valueLoan = valueLoan;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
