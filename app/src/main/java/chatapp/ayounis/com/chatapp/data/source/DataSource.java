package chatapp.ayounis.com.chatapp.data.source;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import chatapp.ayounis.com.chatapp.eventservice.EventListener;
import io.reactivex.Flowable;

public interface DataSource extends EventListener {
    void setEventListener(EventListener eventListener);

    void connect(String username) throws URISyntaxException;

    void disconnect();

    Flowable<ChatMessage> sendMessage(ChatMessage chatMessage);

    void onTyping();

    void onStopTyping();

}
