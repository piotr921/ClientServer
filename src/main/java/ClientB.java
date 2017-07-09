import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ClientB {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public static void main(String[] args) {
        ClientB clientB = new ClientB();
        clientB.run();
    }

    void run() {
        configure();
        Thread clientThread = new Thread(new MessageGetter());
        clientThread.start();
    }

    private void configure() {
        try {
            socket = new Socket("192.168.1.2", 8585);
            // socket = new Socket("10.0.2.15", 8585);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Client configuration completed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendMessageToServer() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write message for server: ");
        String message = scanner.nextLine();

        writer.println(message);
        writer.flush();
    }

    class MessageGetter implements Runnable {

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Read message: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
