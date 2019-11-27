package bank.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Bank
 */
public class Bank {
    Client _cnt;

    public Bank() {
    }

    public Client addClient() throws NumberFormatException, IOException, SQLException, ClassNotFoundException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        DBUtil db = new DBUtil();
        System.out.println("Kindly Provide clientID:");
        int cntID =  Integer.parseInt(br.readLine());
        System.out.print("\nKindly Provide name: ");
        String name = br.readLine();
        System.out.print("\nKindly Provide balance: ");
        float balance = Float.parseFloat(br.readLine());
        boolean cntStatus = db.isClientExist (cntID);
        while (cntStatus) {
            System.out.println("ClientID: "+cntID+" already exist");
            System.out.print("Kindly Provide other clientID: ");
            cntID =  Integer.parseInt(br.readLine());
            cntStatus = db.isClientExist (cntID);
        }
        Client cnt  = new Client(cntID, name, balance);
        _cnt = cnt;
        return cnt;
    }
    
    
}