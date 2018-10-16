package chatapp.ayounis.com.chatapp.eventservice;

import android.util.Log;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class AppEventService implements EventService {

    private static final String TAG = AppEventService.class.getSimpleName();
    private static final String SOCKET_URL = "https://socket-io-chat.now.sh";
    private static final String EVENT_CONNECT = Socket.EVENT_CONNECT;
    private static final String EVENT_DISCONNECT =Socket.EVENT_DISCONNECT;
    private static final String EVENT_CONNECT_ERROR = Socket.EVENT_CONNECT_ERROR;
    private static final String EVENT_CONNECT_TIMEOUT = Socket.EVENT_CONNECT_TIMEOUT;
    private static final String EVENT_NEW_MESSAGE = "new_message";
    private static final String EVENT_USER_JOINED = "user joined";
    private static final String EVENT_USER_LEFT = "user left";
    private static final String EVENT_TYPING = "typing";
    private static final String EVENT_STOP_TYPING = "stop typing";
    private static EventService mInstance;
    private static EventListener mEventListener;
    private static Socket mSocket;
    private String mUsername;

    private AppEventService(){}

    public static EventService getInstance(){
        if(mInstance == null){
            mInstance = new AppEventService();
        }
        return mInstance;
    }

    @Override
    public void connect(String username) throws URISyntaxException {
        mUsername = username;
        mSocket = IO.socket(SOCKET_URL);

        mSocket.emit(EVENT_CONNECT,onConnect);
        mSocket.emit(EVENT_DISCONNECT,onDisconnect);
        mSocket.emit(EVENT_CONNECT_ERROR,onConnectError);
        mSocket.emit(EVENT_CONNECT_TIMEOUT,onConnectError);
        mSocket.emit(EVENT_NEW_MESSAGE,onNewMessage);
        mSocket.emit(EVENT_USER_JOINED,onUserJoined);
        mSocket.emit(EVENT_USER_LEFT,onUserLeft);
        mSocket.emit(EVENT_TYPING,onTyping);
        mSocket.emit(EVENT_STOP_TYPING,onStopTyping);

        mSocket.connect();

    }

    @Override
    public void disconnect() {
        if(mSocket != null)mSocket.disconnect();
    }

    @Override
    public void onTyping() {
        mSocket.emit(EVENT_TYPING);
    }

    @Override
    public void onStopTyping() {
        mSocket.emit(EVENT_STOP_TYPING);
    }

    @Override
    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    @Override
    public Flowable<ChatMessage> sendMessage(final ChatMessage chatMessage) {
        return Flowable.create(new FlowableOnSubscribe<ChatMessage>() {
            @Override
            public void subscribe(FlowableEmitter<ChatMessage> emitter) throws Exception {
                mSocket.emit(EVENT_NEW_MESSAGE,chatMessage.getMessage());
                emitter.onNext(chatMessage);
            }
        },BackpressureStrategy.BUFFER);
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG,"call:onConnect");
            mSocket.emit("add user", mUsername);
            if(mEventListener != null) mEventListener.onConnect(args);
        }
    };
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "call: onDisconnect");
            if (mEventListener != null) mEventListener.onDisconnect(args);
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "call: onConnectError");
            if (mEventListener != null) mEventListener.onConnectError(args);
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onNewMessage(args);
        }
    };

    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onUserJoined(args);
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onUserLeft(args);
        }
    };

    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onTyping(args);
        }
    };

    private Emitter.Listener onStopTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i(TAG, "call: onNewMessage");
            if (mEventListener != null) mEventListener.onStopTyping(args);
        }
    };

}
