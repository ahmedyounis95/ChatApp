package chatapp.ayounis.com.chatapp.chat;

import android.support.annotation.NonNull;
import android.view.View;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.data.ChatMessage;
import chatapp.ayounis.com.chatapp.data.source.Repository;
import chatapp.ayounis.com.chatapp.eventservice.EventListener;
import chatapp.ayounis.com.chatapp.util.schedulers.BaseSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ChatPresenter implements ChatMvpView.Presenter {

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final CompositeDisposable mCompositeDisposable;

    @NonNull
    private final Repository mRepository;

    @NonNull
    private final ChatMvpView.view mView;

    @NonNull
    private final EventListener mViewEventListener;

    public ChatPresenter(@NonNull ChatMvpView.view view,
                         @NonNull EventListener eventListener,
                         @NonNull BaseSchedulerProvider schedulerProvider,
                         @NonNull Repository repository) {

        mView = view;
        mViewEventListener = eventListener;
        mSchedulerProvider = schedulerProvider;
        mRepository = repository;

        mRepository.setEventListener(this);

        mCompositeDisposable = new CompositeDisposable();

        mView.setPresenter(this);

        mView.setUp();
    }

    @Override
    public void sendMessage(ChatMessage chatMessage) {
        Disposable disposable = mRepository.sendMessage(chatMessage)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<ChatMessage>() {
                    @Override
                    public void accept(ChatMessage chatMessage) throws Exception {
                        mView.onMessageDelivered(chatMessage);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showAlert(throwable.getMessage(),true);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void changeUsername(String username) {
        try{
            mRepository.disconnect();
            mRepository.connect(username);
            mView.updateUsername(username);
            mView.showAlert("Username set", false);

        }catch (URISyntaxException e)
        {
            mView.showAlert("changing username failed", true);
            e.printStackTrace();
        }
    }

    @Override
    public void onTyping() {
        mRepository.onTyping();
    }

    @Override
    public void onStopTyping() {
        mRepository.onStopTyping();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onConnect(Object... args) {
        mViewEventListener.onConnect(args);
    }

    @Override
    public void onDisconnect(Object... args) {
        mViewEventListener.onDisconnect(args);
    }

    @Override
    public void onConnectError(Object... args) {
        mViewEventListener.onConnectError(args);
    }

    @Override
    public void onConnectTimeout(Object... args) {
        mViewEventListener.onConnectTimeout(args);
    }

    @Override
    public void onNewMessage(Object... args) {
        mViewEventListener.onNewMessage(args);
    }

    @Override
    public void onUserJoined(Object... args) {
        mViewEventListener.onUserJoined(args);
    }

    @Override
    public void onUserLeft(Object... args) {
        mViewEventListener.onUserLeft(args);
    }

    @Override
    public void onTyping(Object... args) {
        mViewEventListener.onTyping(args);
    }

    @Override
    public void onStopTyping(Object... args) {
        mViewEventListener.onStopTyping(args);
    }
}
