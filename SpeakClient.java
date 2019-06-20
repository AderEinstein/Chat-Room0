import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SpeakClient {

    private Connection server;
    private Scanner in;
    private String myName;

    /**
     * Main Methpd - Creates a new SpeakClient and starts it
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String HOST = "localhost";
        int PORT = ChatServer.PORT;
        String myName = args[0];
        try(Socket socket = new Socket(HOST, PORT)){
            new SpeakClient(socket, myName).startClient();
        }
    }

    /**
     * Constructor for a SpeakClient
     * @param socket
     * @param Name
     * @throws IOException
     */
    public SpeakClient(Socket socket, String Name) throws IOException {
        this.server = new Connection(socket);
        this.myName = Name;
        this.in = new Scanner(System.in);
    }

    /**
     * Prompts for a new message and communicates with ChatWorker
     * @throws IOException
     */
    public void startClient() throws IOException {
        server.writeInt(ChatProtocol.SPEAK);
        while (true) {
            System.out.print("> " + myName + " : ");
            Message message = new Message(myName, in.nextLine());
            server.writeMessage(message);
        }
    }
}