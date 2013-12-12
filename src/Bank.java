import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    public Account[] accounts;
    public int transactionAmount;
    public List<int[]> transactions;

    public int currentTransaction;

    public void getInput(String fileName) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(fileName));

        int accountsNumber = dis.readInt();
        accounts = new Account[accountsNumber];

        for (int j = 0; j < accountsNumber; j++){
            accounts[j] = new Account(dis.readInt());
        }

        transactionAmount = dis.readInt();

        transactions = new ArrayList<int[]>();

        for (int j = 0; j < transactionAmount; j++){
            int[] transaction = new int[3];
            for (int k = 0; k < 3; k++){
                transaction[k] = dis.readInt();
            }
            transactions.add(transaction);
        }

        dis.close();
        currentTransaction = 0;
    }

    public int getSum(){
        int sum = 0;
        for (Account acc : accounts){
            sum += acc.getMoney();
        }

        return sum;
    }

    public int[] getNextTransaction() throws IOException {
        currentTransaction++;
        return transactions.get(currentTransaction - 1);
    }

}
