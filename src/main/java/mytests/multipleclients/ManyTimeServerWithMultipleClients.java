package mytests.multipleclients;

import mytests.simplecases.ManyTimeServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ManyTimeServerWithMultipleClients implements Runnable {

    private ChatServerThread client = null;
    private ServerSocket server = null;
    private Thread thread = null;

    public static void main(String args[]) {
        ManyTimeServer server = new ManyTimeServer(8787);
    }

    public ManyTimeServerWithMultipleClients(int port) {
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
                addThread(server.accept());
            } catch (IOException ie) {
                System.out.println("Acceptance Error: " + ie);
            }
        }
    }

    private void addThread(Socket socket) {
        System.out.println("Client accepted: " + socket);
        client = new ChatServerThread(this, socket);
        try {
            client.open();
            client.start();
        } catch (IOException ioe) {
            System.out.println("Error opening thread: " + ioe);
        }
    }

    private void startThread() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void stopThread() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }
}


