package synchronization;

public class App {

    public static void main(String[] args) {

        SynchronizationTest synchronizationTest = new SynchronizationTest();

        Thread thread = new Thread(synchronizationTest);
        Thread thread1 = new Thread(synchronizationTest);

        thread.start();
        thread1.start();
    }
}
