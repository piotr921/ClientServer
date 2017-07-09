import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {

    ArrayList inputStreams = new ArrayList();

    class ClientHandling implements Runnable {

        BufferedReader reader;
        Socket socket;

        public ClientHandling(Socket socket) {

            try {
                this.socket = socket;
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(inputStreamReader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read message: " + message);
                    sendToEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

    void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8585);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                inputStreams.add(writer);

                Thread thread = new Thread(new ClientHandling(clientSocket));
                thread.start();
                System.out.println("Connected!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendToEveryone(String message) {

        Iterator iterator = inputStreams.iterator();
        while (iterator.hasNext()) {
            PrintWriter writer = (PrintWriter) iterator.next();
            writer.println(message);
            writer.flush();
        }
    }
}
