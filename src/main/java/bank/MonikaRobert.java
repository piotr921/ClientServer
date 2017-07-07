package bank;

public class MonikaRobert implements Runnable {

    BankAccount account = new BankAccount(100);

    public static void main(String[] args) {

        MonikaRobert task = new MonikaRobert();

        Thread thread = new Thread(task);
        Thread thread1 = new Thread(task);

        thread.setName("Monika");
        thread1.setName("Robert");

        thread.start();
        thread1.start();

    }

    @Override
    public void run() {

        for (int i = 0; i < 10 ; i++) {
            takeCash(10);
            if (account.state < 0) {
                System.out.println("Limit exceeded");
            }
        }
    }

    private synchronized void takeCash(int amount) {

        if (account.getState() >= amount) {

            System.out.println(Thread.currentThread().getName() + " wants to take cash.");

            try {
                System.out.println(Thread.currentThread().getName() + " fall asleep.");

                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " wake up");
            account.takeMoney(amount);
            System.out.println(Thread.currentThread().getName() + " finished operation");

        } else {
            System.out.println("Not enough money for thread " + Thread.currentThread().getName());
        }
    }

}
