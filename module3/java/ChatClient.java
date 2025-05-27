import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;
    private volatile boolean running;
    
    public void start() {
        try {
            // Connect to server
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to chat server");
            
            // Initialize streams
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);
            running = true;
            
            // Start message receiver thread
            new Thread(this::receiveMessages).start();
            
            // Process user input
            while (running) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    running = false;
                    out.println(message);
                    break;
                }
                out.println(message);
            }
            
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        } finally {
            stop();
        }
    }
    
    private void receiveMessages() {
        try {
            String message;
            while (running && (message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            if (running) {
                System.out.println("Error receiving message: " + e.getMessage());
            }
        }
    }
    
    public void stop() {
        running = false;
        try {
            if (scanner != null) scanner.close();
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing client: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start();
    }
} 