package mytests.manytomany;

import java.net.Socket;
import java.util.Scanner;

public class ChatClientBThread extends Thread {

    private Socket socket = null;
    private ChatClientB client = null;
    private Scanner scanner = null;

    public ChatClientBThread(ChatClientB _client, Socket _socket) {
        client = _client;
        socket = _socket;
        open();
        start();
    }

    public void open() {
        try {
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("Error getting input stream: " + e);
            client.stop();
        }
    }

    public void close() {
        try {
            if (scanner != null) scanner.close();
        } catch (Exception e) {
            System.out.println("Error closing input stream: " + e);
        }
    }

    public void run() {
        while (true) {
            try {
                client.handle(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Listening error: " + e.getMessage());
                client.stop();
            }
        }
    }
}

