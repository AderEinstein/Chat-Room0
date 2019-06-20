import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
  private final Socket socket;
  private final DataOutputStream output;
  private final DataInputStream input;

  /**
   * Creaates a new Connection from a Socket
   * @param socket
   * @throws IOException
   */
  public Connection(Socket socket) throws IOException {
    this.socket = socket;
    this.input = new DataInputStream(socket.getInputStream());
    this.output = new DataOutputStream(socket.getOutputStream());
  }

  /**
   * reads from server/client (integer commands)
   * @return command read from server/client
   * @throws IOException
   */
  public int readInt() throws IOException {
    return this.input.readInt();
  }
  /**
   * reads from server/client (string commands)
   * @return command read from server/client
   * @throws IOException
   */
  public String readUTF() throws IOException {
    return this.input.readUTF();
  }
  /**
   * Writes to server/client (integer commands)
   * @param i - server/client message
   * @throws IOException
   */
  public void writeInt(int i) throws IOException {
    this.output.writeInt(i);
    this.output.flush();
  }
  /**
   * Writes to server/client (string commands)
   * @param s - server/client message
   * @throws IOException
   */
  public void writeUTF(String s) throws IOException {
    this.output.writeUTF(s);
    this.output.flush();
  }
  /**
   * Writes a messaage command to ChatServer
   * @param m - message
   * @throws IOException
   */
  public void writeMessage(Message m) throws IOException {
    this.writeInt(ChatProtocol.MESSAGE);
    this.writeUTF(m.getWho());
    this.writeUTF(m.getContent());
  }
  /**
   * Closses all connections
   */
  public void close() {
    try {
      this.socket.close();
    } catch (IOException e) {
      // nothing to do
    }
  }
}