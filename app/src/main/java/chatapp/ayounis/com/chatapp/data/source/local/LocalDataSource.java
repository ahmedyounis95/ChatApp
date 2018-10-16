package chatapp.ayounis.com.chatapp.data.source.local;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import chatapp.ayounis.com.chatapp.data.source.DataSource;
import chatapp.ayounis.com.chatapp.data.source.Repository;
import chatapp.ayounis.com.chatapp.eventservice.EventListener;
import io.reactivex.Flowable;

public class LocalDataSource implements DataSource {
    private static LocalDataSource mInstance;

    public static LocalDataSource getInstance(){
        if(mInstance == null)
            mInstance = new LocalDataSource();
        return mInstance;
    }

    @Override
    public void setEventListener(EventListener eventListener) {

    }

    @Override
    public void connect(String username) throws URISyntaxException {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public Flowable<ChatMessage> sendMessage(ChatMessage chatMessage) {
        return null;
    }

    @Override
    public void onTyping() {

    }

    @Override
    public void onStopTyping() {

    }

    @Override
    public void onConnect(Object... args) {

    }

    @Override
    public void onDisconnect(Object... args) {

    }

    @Override
    public void onConnectError(Object... args) {

    }

    @Override
    public void onConnectTimeout(Object... args) {

    }

    @Override
    public void onNewMessage(Object... args) {

    }

    @Override
    public void onUserJoined(Object... args) {

    }

    @Override
    public void onUserLeft(Object... args) {

    }

    @Override
    public void onTyping(Object... args) {

    }

    @Override
    public void onStopTyping(Object... args) {

    }
}
