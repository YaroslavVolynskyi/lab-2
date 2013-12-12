import java.util.concurrent.atomic.AtomicInteger;

public class Account {

    private AtomicInteger money;

    public Account(int cash){
        money = new AtomicInteger(cash);
    }

    public synchronized void putMoney(int cash){
        money.getAndAdd(cash);
    }

    public synchronized int withdrawMoney(int cash){
        money.getAndAdd(-cash);
        return money.get();
    }

    public int getMoney(){
        return money.get();
    }
}
