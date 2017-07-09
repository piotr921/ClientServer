package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PlayerMessanger implements Runnable {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

    }

    private void sendMessageToServer(PrintWriter writer, String communicate) {

        System.out.println(communicate);
        String clientText = scanner.nextLine();

        writer.println(clientText);
        writer.flush();
    }

    private String readNotNullMessageFromServer(BufferedReader reader) {

        String message = "";
        try {
            message = reader.readLine();
            if (message.equals(null)) {
                System.out.println("Null send from server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
