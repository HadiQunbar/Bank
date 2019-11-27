package bank.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bank.core.Account;
import bank.core.Bank;
import bank.core.Client;
import bank.core.DBUtil;
import bank.core.Menu;
import net.hermelin.bank.core.*;

public class MyProgram {

    public static void main(String[] args)  {
        DBUtil mydb = null;    // Create Bank;
        int selection = 0;
        Client cnt = null;
        Account acc = null;
        Bank myBank = new Bank();
        try {
            mydb = new DBUtil();
            selection = Menu.menuSelect();
         
            while (selection!=6) {
                switch (selection) {
                    case 1:{ 
                                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                                cnt = myBank.addClient();
                                System.out.println("Are You Sure you want to add client(Y/N)?");
                                String answer = br.readLine();
                                if((Character.toUpperCase(answer.charAt(0))=='Y'))
                                    {mydb.InsertClient(cnt);}
                }
                        break;
                    case 2:{
                            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                            if (cnt !=null) {
                                acc = cnt.addAccount();

                            }
                            else{
                                cnt = new Client();
                                acc = cnt.addAccount();
                            }  
                           
                            System.out.println("Are You Sure you want to add account(Y/N)?");
                            String answer = br.readLine();
                            if((Character.toUpperCase(answer.charAt(0))=='Y'))
                            {mydb.InsertAccount(acc);}
                    }
                    break; 
                case 3:{mydb.PrintClients();}
                    break;
                case 4:{
                         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                         System.out.println("enter client id:");
                         int id=0;
                         int accNum=0;
                         float amount=0.0f;
                        id = Integer.parseInt(br.readLine());
                        boolean cntStatus = mydb.isClientExist (id);
                        while (cntStatus == false) {
                            System.out.println("ClientID: "+id+" is not exist");
                            System.out.print("Kindly Provide other clientID: ");
                            id =  Integer.parseInt(br.readLine());
                            cntStatus = mydb.isClientExist (id);
                        }
                        System.out.println("enter account number:"); 
                        accNum = Integer.parseInt(br.readLine());
                        boolean accStatus = mydb.isAccountExist(accNum);;
                        while (accStatus == false) {
                            System.out.println("AccountNumber: "+accNum+" is not exist");
                            System.out.print("Kindly Provide other accountNumber: ");
                            accNum =  Integer.parseInt(br.readLine());
                            accStatus = mydb.isAccountExist(accNum);
                        }
                        System.out.println("enter amount to deposit:");  
                        amount = Float.parseFloat(br.readLine());
                        mydb.deposite(id, accNum, amount);
                }
                    break;
                case 5:{
                        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                         System.out.println("enter client id:");
                         int id=0;
                         int accNum=0;
                         float amount=0.0f;
                         id = Integer.parseInt(br.readLine());
                         boolean cntStatus = mydb.isClientExist (id);
                         while (cntStatus == false) {
                             System.out.println("ClientID: "+id+" is not exist");
                             System.out.print("Kindly Provide other clientID: ");
                             id =  Integer.parseInt(br.readLine());
                             cntStatus = mydb.isClientExist (id);
                         }
                         System.out.println("enter account number:");  
                         accNum = Integer.parseInt(br.readLine());
                         boolean accStatus = mydb.isAccountExist(accNum);;
                         while (accStatus == false) {
                            System.out.println("AccountNumber: "+accNum+" is not exist");
                            System.out.print("Kindly Provide other accountNumber: ");
                            accNum =  Integer.parseInt(br.readLine());
                            accStatus = mydb.isAccountExist(accNum);
                         }
                         System.out.println("enter amount to withdraw:");  
                         amount = Float.parseFloat(br.readLine());
                         mydb.withdraw(id, accNum, amount);
                }
                    break;
                default:{System.out.println("Wrong choise :(");}
                    break;
            }  
                selection = Menu.menuSelect();
            }
            System.out.println("Thx you for using our Bank App");
        }       
        catch (NumberFormatException| ClassNotFoundException | SQLException | IOException e1) {
                System.out.println("Error message: "+e1.getMessage());
                e1.printStackTrace();
            }
    }
    }
