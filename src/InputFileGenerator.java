import java.io.*;
import java.util.Random;

public class InputFileGenerator {

    public String generateFile(String fileName, int accountsAmount, int transactionsAmount) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
        dos.writeInt(accountsAmount);
        Random random = new Random();
        int maxCash = 1000;
        for (int i = 0; i < accountsAmount; i++){
            dos.writeInt(random.nextInt(maxCash) + maxCash / 2);
        }

        dos.writeInt(transactionsAmount);

        for (int i = 0; i < transactionsAmount; i++){
            dos.writeInt(random.nextInt(accountsAmount));
            dos.writeInt(random.nextInt(accountsAmount));
            dos.writeInt(random.nextInt(maxCash / 2));
        }

        dos.flush();
        dos.close();

        return fileName;
    }

}
