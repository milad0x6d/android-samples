package ir.milad.androidexamples;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ThreadPoolRunnable implements Runnable {

    private static final String TAG = "ThreadPoolRunnable";
    private WeakReference<Handler> mMainThreadHandler;

    public ThreadPoolRunnable(Handler mMainThreadHandler) {
        this.mMainThreadHandler = new WeakReference<>(mMainThreadHandler);
    }

    @Override
    public void run() {
        Log.d(TAG,"retrieveData, This is from thread: "+Thread.currentThread().getName());
        Message message = Message.obtain(null,0);
        Bundle bundle = new Bundle();
        bundle.putString("data","this is data");
        message.setData(bundle);
        mMainThreadHandler.get().sendMessage(message);
    }
    
    
}
