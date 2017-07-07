package threads;

class MyTask implements Runnable {

    @Override
    public void run() {
        execute();
    }

    void execute() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        anotherJob();
    }

    void anotherJob() {
        System.out.println("Top of stack trace!");
    }
}
