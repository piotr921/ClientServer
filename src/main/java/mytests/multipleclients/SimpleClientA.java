package mytests.multipleclients;

import mytests.simplecases.SimpleClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClientA {

    private Socket socket = null;
    private PrintWriter writer = null;
    private Scanner scanner = null;

    public static void main(String args[]) {
        SimpleClient client = new SimpleClient("192.168.1.2", 8787);
    }

    public SimpleClientA(String serverName, int serverPort) {
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        String line = "";
        while (!line.equals(".bye")) {
            try {
                line = scanner.nextLine();
                writer.println(line);
                writer.flush();
            } catch (Exception e) {
                System.out.println("Sending error: " + e.getMessage());
            }
        }
    }

    private void start() throws IOException {
        writer = new PrintWriter(socket.getOutputStream());
        scanner = new Scanner(System.in);
    }

    private void stop() {
        try {
            if (writer != null) writer.close();
            if (scanner != null) scanner.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
    }
}
