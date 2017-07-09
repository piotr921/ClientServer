package synchronization;

public class SynchronizationTest implements Runnable {

    private int accountState;

    @Override
    public void run() {

        for (int i = 0; i < 500 ; i++) {
            increment();
            System.out.println("Account state is: " + accountState);
        }
    }

    private synchronized void increment() {
        int i = accountState;
        accountState = i + 1;
    }


}
