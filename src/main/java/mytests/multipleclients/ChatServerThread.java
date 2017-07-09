package mytests.multipleclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatServerThread extends Thread {

    private Socket socket = null;
    private ManyTimeServerWithMultipleClients server = null;
    private int ID = -1;
    private BufferedReader clientMessageReader = null;

    public ChatServerThread(ManyTimeServerWithMultipleClients _server, Socket _socket) {
        server = _server;
        socket = _socket;
        ID = socket.getPort();
    }

    public void run() {
        System.out.println("Server Thread " + ID + " running.");
        while (true) {
            try {
                System.out.println(clientMessageReader.readLine());
            } catch (IOException ioe) {
            }
        }
    }

    void open() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        clientMessageReader = new BufferedReader(inputStreamReader);
    }

    void close() throws IOException {
        if (socket != null) socket.close();
        if (clientMessageReader != null) clientMessageReader.close();
    }

}
