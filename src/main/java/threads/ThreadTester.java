package threads;

public class ThreadTester {

    public static void main(String[] args) {
        Runnable threadTask = new MyTask();
        Thread myThread = new Thread(threadTask);

        myThread.start();

        System.out.println("Back in main again");
    }
}
