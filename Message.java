public class Message {
    private String who;
    private String content;

    /**
     * Costructor for a new message
     * @param who - SpeakClient's Name
     * @param content - Message
     */
    public Message(String who, String content) {
        this.who = who;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
