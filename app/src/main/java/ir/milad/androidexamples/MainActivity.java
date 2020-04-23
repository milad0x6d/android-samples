package ir.milad.androidexamples;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ir.milad.androidexamples.threading.RetrieveDataAsyncTask;
import ir.milad.androidexamples.threading.TaskDelegate;

public class MainActivity extends AppCompatActivity implements TaskDelegate {
    private static final String TAG = "MainActivity";
    private RetrieveDataAsyncTask mRetrieveAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRetrieveAsyncTask = new RetrieveDataAsyncTask(this);
        String[] str = {
                "string1",
                "string2",
                "string3"
        };
        mRetrieveAsyncTask.execute(str);
        //new RetrieveDataAsyncTask(this).execute("hello");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // when activity destroy or closed async task also destroyed
        if(mRetrieveAsyncTask != null){
            mRetrieveAsyncTask.cancel(true);//immediately stop async task
        }
    }

    @Override
    public void onRetreieved(ArrayList<String> data) {
        Log.d(TAG,"data retrieved from main activity");
    }
}
