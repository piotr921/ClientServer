import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    void run() {
        try {
            Socket socket = new Socket("192.168.1.2", 8585);

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String readMessage = reader.readLine();
            System.out.println("Read message: " + readMessage);
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
