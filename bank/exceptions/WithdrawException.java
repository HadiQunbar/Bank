package bank.exceptions;

/**
 * WithdrawException
 */
public class WithdrawException extends Exception {

    private static final long serialVersionUID = 1L;
    private int _clientid;
    private float _currentBalance;
    private float _withdrawAmount;
    public WithdrawException(String msg,int clientid,float currentBalance,float withdrawAmount) {
        
        super(msg);
        _clientid = clientid;
        _currentBalance = currentBalance;
        _withdrawAmount = withdrawAmount;
    }
     // * Configure Setter and Getter *
    public String get_clientid() {return Integer.toString(_clientid);}
    public float get_currentBalance() {return _currentBalance;}
    public float get_withdrawAmount() {return _withdrawAmount;}
	public String getClientId() {
		return null;
	}
}