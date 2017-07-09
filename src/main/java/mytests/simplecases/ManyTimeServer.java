package mytests.simplecases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ManyTimeServer implements Runnable {

    private Socket clientSocket = null;
    private ServerSocket server = null;
    private BufferedReader clientMessageReader = null;
    private Thread thread = null;

    public static void main(String args[]) {
        ManyTimeServer server = new ManyTimeServer(8787);
    }

    public ManyTimeServer(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            startThread();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                clientSocket = server.accept();
                System.out.println("Client accepted: " + clientSocket);
                open();
                boolean done = false;
                while (!done) {
                    try {
                        String line = clientMessageReader.readLine();
                        System.out.println(line);
                        done = line.equals(".bye");
                    } catch (IOException ioe) {
                        done = true;
                    }
                }
                close();
            } catch (IOException ie) {
                System.out.println("Acceptance Error: " + ie);
            }
        }
    }

    private void startThread() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stopThread() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }

    private void open() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        clientMessageReader = new BufferedReader(inputStreamReader);
    }

    private void close() throws IOException {
        if (clientSocket != null) clientSocket.close();
        if (clientMessageReader != null) clientMessageReader.close();
    }
}
