package bank.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bank.exceptions.WithdrawException;
import net.hermelin.bank.exceptions.*;

/**
 * Client
 */
public class Client {

    int _id;
    String _name;
    float _balance;
    float _commission_rate = 0;
    float _interest_rate = 0;

    public Client(int id, String name, float balance) {
        _id = id;
        _name = name;
        _balance = balance;
    }

    public Client() {
    };

    public int getId() {
        return _id;
    }

    public float getBalance() {
        return _balance;
    }

    public String getName() {
        return _name;
    }

    public void setBalance(float balance) {
        _balance = balance;
    }

    public void setName(String name) {
        _name = name;
    }

    public void deposit(float amount, DBUtil db, int accnumber) throws SQLException, IOException
    {
         _balance += amount - (amount *_commission_rate);
         db.deposite(this._id, accnumber, _balance);

    }

    public void withdraw(float amount, DBUtil db,int accnum) throws WithdrawException, SQLException, IOException 
    { 
        if (amount > _balance) {
            throw new WithdrawException("You dont have sufficient money to do this operation",_id, _balance, amount);
        }
        else{
                _balance -= amount - (amount * _commission_rate);
                db.withdraw(_id, accnum, _balance);
            }
    }

    public Account addAccount() throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        DBUtil db = new DBUtil();
        System.out.print("\nKindly Provide Account Number: ");
        int accountNumber = Integer.parseInt(br.readLine());
        System.out.print("\nKindly Provide balance: ");
        float balance = Float.parseFloat(br.readLine());;
        boolean accStatus = db.isAccountExist(accountNumber);;
        while (accStatus) {
            System.out.println("AccountNumber: "+accountNumber+" already exist");
            System.out.print("Kindly Provide other accountNumber: ");
            accountNumber =  Integer.parseInt(br.readLine());
            accStatus = db.isAccountExist(accountNumber);
        }
        System.out.print("\nDo you want to use the clientID number: "+_id+" (Y/N)?");
        String answer = br.readLine();
        if((Character.toUpperCase(answer.charAt(0))=='Y'))
        {
            return new Account(accountNumber, balance,_id);
        }
        else {

            System.out.print("\nKindly Provide ClientID Number: ");
            int clientIDnum = Integer.parseInt(br.readLine());;
            return new Account(accountNumber, balance,clientIDnum);
        }
    }
    
    @Override
    public String toString() {
        return "ClientID:"+_id;
    }
    





}