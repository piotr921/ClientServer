package mytests.simplecases;

import java.net.*;
import java.io.*;

public class OneTimeServer {

    private Socket clientSocket = null;
    private ServerSocket server = null;
    private BufferedReader clientMessageReader = null;

    public static void main(String args[]) {
        OneTimeServer server = new OneTimeServer(8787);
    }

    public OneTimeServer(int port) {
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
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
        } catch (IOException ioe) {
            System.out.println(ioe);
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
