package threads;

class MyTask implements Runnable {

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        anotherJob();
    }

    public void anotherJob() {
        System.out.println("Top of stack trace!");
    }
}
