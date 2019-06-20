import java.io.IOException;
import java.util.ArrayList;

public class ChatRoom {

    private ArrayList<Message> messages;
    private ArrayList<Connection> listeners;

    /**
     * Constructor for a new ChatRoom
     */
    public ChatRoom(){
        this.messages = new ArrayList<Message>();
        this.listeners = new ArrayList<Connection>();
    }

    /**
     * adds messages to Message List
     * @param message
     * @throws IOException
     */
    public synchronized void addMessage(Message message) throws IOException {
        this.messages.add(message);
        for(Connection x : listeners){
            x.writeMessage(message);
        }
    }
    public synchronized ArrayList<Message> getMessages(){ return this.messages; }

    /**
     * adds Listeners to Listeners List
     * @param s - a new Listener Connection
     */
    public synchronized void attachListener(Connection s){this.listeners.add(s); }

    public ArrayList<Connection> getListeners(){ return this.listeners; }

}