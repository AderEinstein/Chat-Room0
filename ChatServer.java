import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    public static final int PORT = 46464;
    private final ServerSocket serverSocket;
    private ChatRoom chatRoom;

    /**
     * Constructor for a ChatServer
     * @param serverSocket
     */
    public ChatServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.chatRoom = new ChatRoom();
    }

    /**
     * Main method - Instantiates a ChatServer by providing it with a ServerSocket
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            new ChatServer(serverSocket).startServer();
        }
    }

    /**
     * Creates new a new ChatWorker to take care of every new client that connects to the ChatSerer
     * @throws IOException
     */
    public void startServer() throws IOException {

        while (true){
            Socket s = this.serverSocket.accept();
            Runnable r = new ChatWorker(new Connection(s), this.chatRoom);
            new Thread(r).start();
        }
    }
}
