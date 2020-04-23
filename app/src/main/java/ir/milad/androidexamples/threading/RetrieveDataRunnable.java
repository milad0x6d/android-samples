package ir.milad.androidexamples.threading;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

// runnable is unit of work. unit of work can be passed to thread to completion
// it can run on any thread including main thread
public class RetrieveDataRunnable implements Runnable {

    //private Handler mMainThreadHandler;
    private WeakReference<Handler> mMainThreadHandler;// weak is memory leak solution
    private static final String TAG = "RetrieveDataRunnable";

//    public RetrieveDataRunnable(WeakReference<Handler>  mMainThreadHandler) {
//        this.mMainThreadHandler = mMainThreadHandler;
//    }


    public RetrieveDataRunnable(Handler mMainThreadHandler) {
        this.mMainThreadHandler = new WeakReference<>(mMainThreadHandler);
    }

    @Override
    public void run() {
        Log.d(TAG,"data retrieved. This is from thread: "+ Thread.currentThread().getName());
        Message message = Message.obtain(null,0);
        mMainThreadHandler.get().sendMessage(message);
    }
}
