package chatapp.ayounis.com.chatapp.eventservice;

public interface EventListener {

    void onConnect(Object... args);

    void onDisconnect(Object... args);

    void onConnectError(Object... args);

    void onConnectTimeout(Object... args);

    void onNewMessage(Object... args);

    void onUserJoined(Object... args);

    void onUserLeft(Object... args);

    void onTyping(Object... args);

    void onStopTyping(Object... args);
}
