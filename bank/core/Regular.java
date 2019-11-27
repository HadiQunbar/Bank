package bank.core;
/**
 * Regular
 */
public class  Regular extends Client {

    public  Regular(int id,String name,float balance) {
        super(id,name,balance);
        super._commission_rate = 0.03f;
        super._interest_rate = 0.001f;

    }
    @Override
    public String toString() {
        return super.toString()+" Class type:"+this.getClass().getSimpleName();
    }
}