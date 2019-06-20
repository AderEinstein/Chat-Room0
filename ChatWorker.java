import java.io.IOException;

public class ChatWorker implements Runnable  {

    private ChatRoom chatRoom;
    private Connection clientConnection;
    private int clientType;

    /**
     * Constructor for a new ChatWorker
     * @param clientConnection - a SpeakClient or ListenClient
     * @param chatRoom - Keeps track of the added ListenClients and the messages sent by every SpeakClient
     */
    public ChatWorker(Connection clientConnection, ChatRoom chatRoom){
        this.chatRoom = chatRoom;
        this.clientConnection = clientConnection;
    }

    /**
     * Communicates and executes tasks from the connected client
     * @throws IOException
     */
    public void startWorking() throws IOException {

        this.clientType = this.clientConnection.readInt();

        while(true){
            if(clientType == ChatProtocol.LISTEN){
                this.provideMessageHistory();
                chatRoom.attachListener(clientConnection);
                break;
            }
            else if(clientType == ChatProtocol.SPEAK){
                int command = this.clientConnection.readInt();
                if(command == ChatProtocol.MESSAGE) {
                    String speaker = clientConnection.readUTF();
                    String message = clientConnection.readUTF();
                    this.handleMessage(speaker, message);
                }
            }
        }
        //infinite Loop so unnecessary to close connection
        //clientConnection.close();
    }
    private void provideMessageHistory() throws IOException {
        for(Message m : chatRoom.getMessages()){
            this.clientConnection.writeMessage(m);
        }
    }
    private void handleMessage(String speaker, String message) throws IOException {
        chatRoom.addMessage(new Message(speaker, message));
    }

    @Override
    /**
     * starts ChatWorker action
     */
    public void run() {
        try {
            this.startWorking();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}