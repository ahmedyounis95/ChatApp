package chatapp.ayounis.com.chatapp;

import android.app.Application;
import chatapp.ayounis.com.chatapp.util.AppLifeCycleObserver;

public class ChatApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppLifeCycleObserver appLifeCycleObserver = new AppLifeCycleObserver(getApplicationContext());

       /* ProcessLifecycleOwner.get()
                .getLifecycle()
                .addObserver(appLifeCycleObserver);
*/

    }
}
