
package chatapp.ayounis.com.chatapp.data.source.remote;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import chatapp.ayounis.com.chatapp.data.source.DataSource;
import chatapp.ayounis.com.chatapp.eventservice.EventServiceImpl;
import chatapp.ayounis.com.chatapp.eventservice.EventListener;
import chatapp.ayounis.com.chatapp.eventservice.EventService;
import io.reactivex.Flowable;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;
    private static EventService mEventService = EventServiceImpl.getInstance();
    private EventListener mRepoEventListener;

    private RemoteDataSource() {
        mEventService.setEventListener(this);
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }

        return INSTANCE;
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

    @Override
    public void setEventListener(EventListener eventListener) {
        mRepoEventListener = eventListener;
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
    public void connect(String username) throws URISyntaxException {
        mEventService.connect(username);
    }

    @Override
    public void disconnect() {
        mEventService.disconnect();
    }
}
