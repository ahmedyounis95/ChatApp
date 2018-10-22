package chatapp.ayounis.com.chatapp.chat;

import chatapp.ayounis.com.chatapp.BasePresenter;
import chatapp.ayounis.com.chatapp.BaseView;
import chatapp.ayounis.com.chatapp.data.ChatMessage;
import chatapp.ayounis.com.chatapp.eventservice.EventListener;

public interface ChatMvpView {

    interface view extends BaseView<Presenter>,EventListener{

        void onMessageDelivered(ChatMessage chatMessage);

        void updateUsername(String username);
    }
    interface Presenter extends BasePresenter,EventListener{

        void sendMessage(ChatMessage chatMessage);

        void changeUsername(String username);

        void onTyping();

        void onStopTyping();
    }
}
