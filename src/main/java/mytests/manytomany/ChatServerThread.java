package mytests.manytomany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatServerThread extends Thread {

    private ChatServer server = null;
    private Socket socket = null;
    private int ID = -1;

    private BufferedReader reader = null;
    private PrintWriter writer = null;

    ChatServerThread(ChatServer _server, Socket _socket) {
        super();
        server = _server;
        socket = _socket;
        ID = socket.getPort();
    }

    @Override
    public void run() {
        System.out.println("Server Thread " + ID + " running.");
        while (true) {
            try {
                server.handle(ID, reader.readLine());
            } catch (IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.remove(ID);
                stop();
            }
        }
    }

    void send(String msg) {
        try {
            writer.println(msg);
            writer.flush();
        } catch (Exception e) {
            System.out.println(ID + " ERROR sending: " + e.getMessage());
            server.remove(ID);
            stop();
        }
    }

    int getID() {
        return ID;
    }

    void open() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(inputStreamReader);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        writer = new PrintWriter(outputStreamWriter);
    }

    void close() throws IOException {
        if (socket != null) socket.close();
        if (reader != null) reader.close();
        if (writer != null) writer.close();
    }
}
