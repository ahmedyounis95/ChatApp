package chatapp.ayounis.com.chatapp.eventservice;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import io.reactivex.Flowable;

public interface EventService {

    void connect(String username) throws URISyntaxException;

    void disconnect();

    void setEventListener(EventListener eventListener);

    Flowable<ChatMessage> sendMessage(ChatMessage chatMessage);

    void onTyping();

    void onStopTyping();
}
