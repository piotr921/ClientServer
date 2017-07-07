package threads;

public class NamedThreads implements Runnable{

    public static void main(String[] args) {

        NamedThreads program = new NamedThreads();
        Thread alpha = new Thread(program);
        Thread beta = new Thread(program);

        alpha.setName("alpha");
        beta.setName("beta");

        alpha.start();
        beta.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 50 ; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println("Now is executing: " + threadName);
        }
    }
}
