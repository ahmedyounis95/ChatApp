package chatapp.ayounis.com.chatapp.data.source;

import android.support.annotation.NonNull;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import chatapp.ayounis.com.chatapp.eventservice.EventListener;
import io.reactivex.Flowable;

public class Repository implements DataSource {

    private static Repository mInstance = null;
    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;
    private EventListener mPresenterListener;

    private Repository(@NonNull DataSource mRemoteDataSource, @NonNull DataSource mLocalDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
        this.mLocalDataSource = mLocalDataSource;
        mRemoteDataSource.setEventListener(this);
    }
    public static Repository getInstance(@NonNull DataSource remoteDataSource,@NonNull DataSource localDataSource){
        if(mInstance == null){
            mInstance = new Repository(remoteDataSource,localDataSource);
        }
        return mInstance;
    }

    @Override
    public void setEventListener(EventListener eventListener) {
        mPresenterListener = eventListener;
    }

    @Override
    public void connect(String username) throws URISyntaxException {
        mRemoteDataSource.connect(username);
    }

    @Override
    public void disconnect() {
        mRemoteDataSource.disconnect();
    }

    @Override
    public Flowable<ChatMessage> sendMessage(ChatMessage chatMessage) {
        return mRemoteDataSource.sendMessage(chatMessage);
    }

    @Override
    public void onTyping() {
        mRemoteDataSource.onTyping();
    }

    @Override
    public void onStopTyping() {
        mRemoteDataSource.onStopTyping();
    }

    @Override
    public void onConnect(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onConnect(args);
    }

    @Override
    public void onDisconnect(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onDisconnect(args);
    }

    @Override
    public void onConnectError(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onConnectError(args);
    }

    @Override
    public void onConnectTimeout(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onConnectTimeout(args);
    }

    @Override
    public void onNewMessage(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onNewMessage(args);
    }

    @Override
    public void onUserJoined(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onUserJoined(args);
    }

    @Override
    public void onUserLeft(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onUserLeft(args);
    }

    @Override
    public void onTyping(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onTyping(args);
    }

    @Override
    public void onStopTyping(Object... args) {
        if(mPresenterListener != null)
            mPresenterListener.onStopTyping(args);
    }
}
