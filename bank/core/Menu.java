package bank.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Menu
 */
public class Menu {

    public static int menuSelect() throws NumberFormatException, IOException {

        int selection;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        /***************************************************/

        System.out.println("\nChoose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create Client");
        System.out.println("2 - Create Account");
        System.out.println("3 - List Of All Clients");
        System.out.println("4 - Deposit amount in account");
        System.out.println("5 - Withdraw amount from account");
        System.out.println("6 - Exit\n");
        System.out.print("Your choice :");
        selection = Integer.parseInt(br.readLine()); 
        return selection;    
    }
    }