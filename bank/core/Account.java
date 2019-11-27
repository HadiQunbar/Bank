package bank.core;

/**
 * Account
 */
public class Account {

    int _id;
    float _balance;
    int _clientID;
    public Account(int id,float balance,int clientID) {
        _id = id;
        _balance = balance;
        _clientID = clientID;
    }
    public Account() {
        super();
    }
    public int getId() {return _id;}
    public float getBalance() {return _balance;}
    public void setId(int id) {
        _id = id;
    }
    public void setBalance(float balance) {
        _balance = balance;
    }
}