package chatapp.ayounis.com.chatapp.data.source.remote;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import chatapp.ayounis.com.chatapp.data.source.DataSource;
import chatapp.ayounis.com.chatapp.eventservice.AppEventService;
import chatapp.ayounis.com.chatapp.eventservice.EventListener;
import chatapp.ayounis.com.chatapp.eventservice.EventService;
import io.reactivex.Flowable;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource mInstance;
    private static EventService mEventService = AppEventService.getInstance();
    private EventListener mRepoEventListener;

    private RemoteDataSource(){
        mEventService.setEventListener(this);
    }
    public static RemoteDataSource getInstance() {
        if(mInstance == null)
            mInstance = new RemoteDataSource();
        return mInstance;

    }
    @Override
    public void setEventListener(EventListener eventListener) {
        mRepoEventListener = eventListener;
    }

    @Override
    public void connect(String username) throws URISyntaxException {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnect(username);
    }

    @Override
    public void disconnect() {
        if (mRepoEventListener != null)
            mRepoEventListener.onDisconnect();
    }

    @Override
    public Flowable<ChatMessage> sendMessage(ChatMessage chatMessage) {
        return mEventService.sendMessage(chatMessage);
    }

    @Override
    public void onTyping() {
        mEventService.onTyping();
    }

    @Override
    public void onStopTyping() {
        mEventService.onStopTyping();
    }

    @Override
    public void onConnect(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnect(args);
    }

    @Override
    public void onDisconnect(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onDisconnect(args);
    }

    @Override
    public void onConnectError(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnectError(args);
    }

    @Override
    public void onConnectTimeout(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnectTimeout(args);
    }

    @Override
    public void onNewMessage(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onNewMessage(args);
    }

    @Override
    public void onUserJoined(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onUserJoined(args);
    }

    @Override
    public void onUserLeft(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onUserLeft(args);
    }

    @Override
    public void onTyping(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onTyping(args);
    }

    @Override
    public void onStopTyping(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onStopTyping(args);
    }
}
