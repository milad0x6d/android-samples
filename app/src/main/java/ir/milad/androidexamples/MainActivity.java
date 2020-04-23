package ir.milad.androidexamples;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import ir.milad.androidexamples.R;
import ir.milad.androidexamples.ThreadPoolRunnable;

public class MainActivity extends AppCompatActivity implements Handler.Callback{

    private static final String TAG = "MainActivity";

    private ExecutorService mExecutorService;
    //private ThreadPoolExecutor mThreadPoolExecutor; extended by executorservice and has more option like max pool size and...

    private Handler mMainThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initExecutorThreadPool();
        mMainThreadHandler = new Handler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        executeThreadPool();
    }

    private void initExecutorThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        Log.d(TAG, "initExecutorThreadPool, cores: " + cores);
        mExecutorService = Executors.newFixedThreadPool(cores);
    }

    private void executeThreadPool() {
        ThreadPoolRunnable threadPoolRunnable = new ThreadPoolRunnable(mMainThreadHandler);
        mExecutorService.submit(threadPoolRunnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mExecutorService.shutdownNow();// stop thread immediately
        mExecutorService.shutdown();// stop thread after tasks done
    }

    @Override
    public boolean handleMessage(Message message) {
        if(message.what == 0)
            Log.d(TAG, "data retrieved in MainActivity, Thread: " + Thread.currentThread().getName());
        return false;
    }
}
