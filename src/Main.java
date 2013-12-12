import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    static ExecutorService executor;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("aldk");
        String fileName = "input.bin";
        InputFileGenerator fileGenerator = new InputFileGenerator();
        String input = fileGenerator.generateFile(fileName, 10000, 10000);
        Bank bank = new Bank();
        bank.getInput(input);

        executor = new ThreadPoolExecutor(
                3,  // core pool size
                6, // max pool size
                1000L, TimeUnit.MILLISECONDS, // keep alive
                new LinkedBlockingQueue<Runnable>(100),    // queue
                new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("sum before = " + bank.getSum());

        long start = System.currentTimeMillis();

        while(bank.currentTransaction < bank.transactionAmount){
            executor.submit(new Consumer(bank, bank.getNextTransaction()));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("sum after = " + bank.getSum());
        System.out.println("time = " + (System.currentTimeMillis() - start));
    }

}
