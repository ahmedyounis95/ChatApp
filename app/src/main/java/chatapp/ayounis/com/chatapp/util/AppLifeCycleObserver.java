package chatapp.ayounis.com.chatapp.util;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.widget.Toast;

import java.net.URISyntaxException;

import chatapp.ayounis.com.chatapp.eventservice.EventServiceImpl;

public class AppLifeCycleObserver implements LifecycleObserver {
    private Context mContext;

    public AppLifeCycleObserver(Context context) {
        this.mContext = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground(){
        try{
            EventServiceImpl.getInstance().connect(User.getUsername());
        }catch (URISyntaxException e){
            Toast.makeText(mContext, "Failed to connect to chat server", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground(){EventServiceImpl.getInstance().disconnect();}
}
