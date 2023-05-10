package bancobackend.model;


import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "idClient", unique = true)
    private String idClient;

    @Column(name = "nameClient", nullable = false)
    private String nameClient;

    @Column(name = "lastNameClient", nullable = false)
    private String lastNameClient;

    @Column(name = "scoreCredit", nullable = false)
    private int scoreCredit;

    @Column(name = "password", nullable = false)
    private String password;

    public Client() {

    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public int getScoreCredit() {
        return scoreCredit;
    }

    public void setScoreCredit(int scoreCredit) {
        this.scoreCredit = scoreCredit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
