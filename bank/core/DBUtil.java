package bank.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * DbConnection
 */
public class DBUtil {

    Connection _con;

    public DBUtil() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        final String DB_TYPE = "jdbc:mysql://localhost:3306/bank";
        final String DB_USER_NAME = "root";
        final String DB_PASSWORD_NAME = "";
        Connection con = DriverManager.getConnection(DB_TYPE, DB_USER_NAME, DB_PASSWORD_NAME);
        System.out.println("Mysql Connection Success");
        _con = con;
    }

    public void InsertClient(Client cnt) throws SQLException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sqlQuery = "insert into client values(?,?,?)";
        PreparedStatement pstmt = _con.prepareStatement(sqlQuery);
        pstmt.setInt(1, cnt._id);
        pstmt.setString(2, cnt._name);
        pstmt.setFloat(3, cnt._balance);
        int i = pstmt.executeUpdate();
        System.out.println(i + " records inserted");
        System.out.println("Client ID: " + cnt._id + " add in the system information.");
        System.out.print("\nPlease Enter any key to continue: ");
        br.readLine();
        pstmt.close();
    }

    public boolean isClientExist(int clientID) throws SQLException {

        String sqlQuery = "SELECT count(client.`clientID`) FROM CLIENT WHERE clientid = ?";
        PreparedStatement pstmt = _con.prepareStatement(sqlQuery);
        pstmt.setInt(1, clientID);
        ResultSet rs = pstmt.executeQuery();
        rs.absolute(1);
        int res = rs.getInt(1);
        rs.close();
        pstmt.close();
        if (res > 0) {
            return true;
        }
        return false;
    }

    public boolean isAccountExist(int accNum) throws SQLException {

        String sqlQuery = "SELECT COUNT(account.`account_number`) FROM account WHERE account_number = ?";
        PreparedStatement pstmt = _con.prepareStatement(sqlQuery);
        pstmt.setInt(1, accNum);
        ResultSet rs = pstmt.executeQuery();
        rs.absolute(1);
        int res = rs.getInt(1);
        rs.close();
        pstmt.close();
        if (res > 0) {
            return true;
        }
        return false;
    }

    public void InsertAccount(Account acc) throws SQLException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sqlQuery = "insert into account values(?,?,?)";
        PreparedStatement pstmt = _con.prepareStatement(sqlQuery);
        pstmt.setInt(1, acc._clientID);
        pstmt.setInt(2, acc._id);
        pstmt.setFloat(3, acc._balance);
        int i = pstmt.executeUpdate();
        System.out.println(i + " records inserted");
        System.out.println(
                "Account number" + acc._id + " added for Client Id:" + acc._clientID + " in the system information.");
        System.out.print("\nPlease Enter any key to continue: ");
        br.readLine();
        pstmt.close();
    }

    public void PrintClients() throws SQLException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sqlQuery = "SELECT c.`clientID` AS 'Client ID',a.`account_number`";
        sqlQuery += " AS 'Account Number',";
        sqlQuery += "c.`name` AS 'Account Name' ,c.`balance` AS 'Client Balamce',";
        sqlQuery += "a.`balance` AS 'Account Balance'";
        sqlQuery += "FROM CLIENT c LEFT OUTER JOIN account a ";
        sqlQuery += "ON c.`clientID` = a.`clientid`";
        PreparedStatement pstmt=_con.prepareStatement(sqlQuery);
        ResultSet rs=pstmt.executeQuery();  
        while(rs.next()){  
            System.out.println("ClientID: "+rs.getInt(1)+" |Account Number"+rs.getInt(2)+
                              " |Client Name: "+rs.getString(3)+" |Client Balance: "+
                              rs.getFloat(4)+" |Account Balance: "+rs.getFloat(5)
                              );  
            }
        rs.close();
        pstmt.close();  
        System.out.print("\nPlease Enter any key to continue: ");
        br.readLine();
        
    }

    public void deposite(int cntID,int accNum,float balance) throws SQLException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sqlQuery = "update account a set a.`balance` = ? WHERE a.`clientid` = ? ";
        sqlQuery += "AND a.`account_number` = ?";
        PreparedStatement pstmt_select=_con.prepareStatement("SELECT account.`balance` FROM account WHERE account.`account_number`= ?");
        pstmt_select.setInt(1,accNum);
        ResultSet rs=pstmt_select.executeQuery();
        rs.absolute(1);
        float currBalance = rs.getFloat(1);
        balance += currBalance;
        PreparedStatement pstmt=_con.prepareStatement(sqlQuery);
        pstmt.setFloat(1, balance);
        pstmt.setInt(2,cntID);
        pstmt.setInt(3, accNum);
        int i = pstmt.executeUpdate();
        System.out.println(i+" records inserted");
        System.out.println("amount of "+balance+" updated in the system");
        System.out.print("\nPlease Enter any key to continue: ");
        br.readLine();
        rs.close();
        pstmt_select.close();
        pstmt.close();
    }

    public void withdraw(int cntID,int accNum,float balance) throws SQLException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sqlQuery = "update account a set a.`balance` = ? WHERE a.`clientid` = ? ";
        sqlQuery += "AND a.`account_number` = ?";
        PreparedStatement pstmt_select=_con.prepareStatement("SELECT account.`balance` FROM account WHERE account.`account_number`= ?");
        pstmt_select.setInt(1,accNum);
        ResultSet rs=pstmt_select.executeQuery();
        rs.absolute(1);
        float currBalance = rs.getFloat(1);
        currBalance -= balance;
        PreparedStatement pstmt=_con.prepareStatement(sqlQuery);
        pstmt.setFloat(1, currBalance);
        pstmt.setInt(2,cntID);
        pstmt.setInt(3, accNum);
        int i = pstmt.executeUpdate();
        System.out.println(i+" records inserted");
        System.out.println("amount of "+balance+" withdraw in the system");
        System.out.print("\nPlease Enter any key to continue: ");
        br.readLine();
        rs.close();
        pstmt_select.close();
        pstmt.close();
    }
    
    
}