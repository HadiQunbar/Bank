package bank.core;
/**
 * Platinum
 */
public class Platinum extends Client {

    public Platinum(int id,String name,float balance) {
        super(id,name,balance);
        super._commission_rate = 0.01f;
        super._interest_rate = 0.005f;
    }
    @Override
    public String toString() {
        return super.toString()+" Class type:"+this.getClass().getSimpleName();
    }
}