import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ListenClient {

    private Connection server;

    /**
     * Main Method - Creates a new ListenClient and Starts it
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int PORT = ChatServer.PORT;
        String HOST = "localhost";
        try(Socket socket = new Socket(HOST, PORT)){
            new ListenClient(socket).startClient();
        }
    }

    /**
     * Constructor for a new Listen Client
     * @param socket
     * @throws IOException
     */
    public ListenClient(Socket socket) throws IOException {
        this.server = new Connection(socket);
    }

    /**
     * Communicates with ChatWorker and prints out every message sent by the SpeakClients
     * @throws IOException
     */
    public void startClient() throws IOException {

        server.writeInt(ChatProtocol.LISTEN);

        while (true){
            int command = server.readInt();
            if(command == ChatProtocol.MESSAGE){
                String sender = server.readUTF();
                String content = server.readUTF();
                this.handleMessage(sender, content);
            }
        }

    }
    private void handleMessage(String sender, String content){
        System.out.println("> " + sender + " : " + content );
    }

}
