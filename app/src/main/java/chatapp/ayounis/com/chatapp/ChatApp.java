package chatapp.ayounis.com.chatapp;

import android.app.Application;
import android.arch.lifecycle.ProcessLifecycleOwner;

import chatapp.ayounis.com.chatapp.util.AppLifeCycleObserver;

public class ChatApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppLifeCycleObserver appLifeCycleObserver = new AppLifeCycleObserver(getApplicationContext());

        ProcessLifecycleOwner.get()
                .getLifecycle()
                .addObserver(appLifeCycleObserver);
    }
}
