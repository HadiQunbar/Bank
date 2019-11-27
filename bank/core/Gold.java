package bank.core;
/**
 * Gold
 */
public class Gold extends Client {

    public Gold(int id,String name,float balance) {
        super(id,name,balance);
        super._commission_rate = 0.02f;
        super._interest_rate = 0.003f;
        
    }

    @Override
    public String toString() {
        return super.toString()+" Class type:"+this.getClass().getSimpleName();
    }
}