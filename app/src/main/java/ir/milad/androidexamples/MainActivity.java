package ir.milad.androidexamples;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ir.milad.androidexamples.threading.RetrieveDataRunnable;


// handler has built-in function to prepare looper and start looper
public class MainActivity extends AppCompatActivity
    implements Handler.Callback
{
    private static final String TAG = "MainActivity";

    private Handler mMainThreadHandler;
    private HandlerThread mHandlerThread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainThreadHandler = new Handler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandlerThread = new HandlerThread("MainActivity HandlerThread");
        mHandlerThread.start();
        getData();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandlerThread.quit();//quit immediately
        //mhandlerThread.quitSafely();// finish all messages in message queue and quit
    }

    private void getData(){
        Handler backgroundHandler = new Handler(mHandlerThread.getLooper());
        backgroundHandler.post(new RetrieveDataRunnable(mMainThreadHandler));
    }


    @Override
    public boolean handleMessage(Message message) {
        Log.d(TAG,"data retrieved. This is from thread: "+ Thread.currentThread().getName());
        return false;
    }
}
