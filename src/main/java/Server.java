import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Server {

    List<String> messages = Arrays.asList("AAA", "BBB", "CCC", "DDD", "EEE", "FFF");

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

    void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8585);

            while (true) {
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                System.out.println("server sending a message: ");
                String message = getRandom(messages);
                writer.println(message);
                writer.close();
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getRandom(List<String> strings) {
        int random = (int) (Math.random() * strings.size());
        return strings.get(random);
    }
}
