package chatapp.ayounis.com.chatapp.util;

import android.content.Context;

import chatapp.ayounis.com.chatapp.data.source.Repository;
import chatapp.ayounis.com.chatapp.data.source.local.LocalDataSource;
import chatapp.ayounis.com.chatapp.data.source.remote.RemoteDataSource;
import chatapp.ayounis.com.chatapp.util.schedulers.BaseSchedulerProvider;
import chatapp.ayounis.com.chatapp.util.schedulers.SchedulerProvider;

public class Injection {
    public static Repository providesRepository(Context context){
        return Repository.getInstance(RemoteDataSource.getInstance(),
                LocalDataSource.getInstance());
    }
    public static BaseSchedulerProvider provideSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }
}
