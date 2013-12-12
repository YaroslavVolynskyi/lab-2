
public class Consumer implements Runnable{

    int[] transaction;
    Bank bank;

    public Consumer(Bank bank, int[] transaction){
        this.transaction = transaction;
        this.bank = bank;
    }

    @Override
    public void run(){
        bank.accounts[transaction[0]].withdrawMoney(transaction[2]);
        bank.accounts[transaction[1]].putMoney(transaction[2]);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("from: " + transaction[0] + " to: " + transaction[1] + " amount: " + transaction[2]);
    }
}
