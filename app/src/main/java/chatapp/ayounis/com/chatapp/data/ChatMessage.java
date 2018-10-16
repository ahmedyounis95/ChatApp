
package chatapp.ayounis.com.chatapp.data;

public class ChatMessage {
    public static final int TYPE_MESSAGE_SENT = 393;
    public static final int TYPE_MESSAGE_RECEIVED = 529;

    private String username;
    private String message;
    private int type;

    public ChatMessage(String username, String message, int type) {
        this.username = username;
        this.message = message;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
