package ir.milad.androidexamples;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ir.milad.androidexamples.threading.MyThread;

public class MainActivity extends AppCompatActivity implements Handler.Callback{
    private MyThread mMyThread;
    private static final String TAG = "MainActivity";

    Handler mMainThreadHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainThreadHandler = new Handler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyThread = new MyThread(mMainThreadHandler);
        mMyThread.start();
    }

    private void sendTestMessage(){
        Log.d(TAG,"sendTestMessage: sending test message: "+ Thread.currentThread().getName());
        Message message  = Message.obtain(null,Constants.WORD_INSERT_NEW);
        mMyThread.sendMessageToBackgroundThread(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sendTestMessage();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMyThread.quitThread();
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case Constants.WORD_INSERT_NEW : {
                Log.d(TAG,"handleMessage: saving word on thread : " + Thread.currentThread().getName());
                break;
            }
            case Constants.WORD_UPDATE : {
                Log.d(TAG,"handleMessage: updating word on thread : " + Thread.currentThread().getName());
                break;
            }
            case Constants.WORD_RETRIEVE : {
                Log.d(TAG,"handleMessage: retrieving word on thread : " + Thread.currentThread().getName());
                break;
            }
            case Constants.WORD_DELETE : {
                Log.d(TAG,"handleMessage: deleting word on thread : " + Thread.currentThread().getName());
                break;
            }
        }
        return false;
    }
}
