package ir.milad.androidexamples.threading;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

// first : input
// second : onprogress
// third : result (onpostexecute)

//downside : execute one at a time - linear thread execution - no good for long-running tasks
public class RetrieveDataAsyncTask extends AsyncTask<String, Integer, ArrayList<String>> {

    private static final String TAG = "RetrieveDataAsyncTask";
    private WeakReference<TaskDelegate> mDelegate;

    public RetrieveDataAsyncTask(TaskDelegate mDelegate) {
        this.mDelegate = new WeakReference<>(mDelegate);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // On Main Thread
    }

    @Override
    protected void onPostExecute(ArrayList<String> data) {
        super.onPostExecute(data);
        // On Main Thread
        mDelegate.get().onRetreieved(data);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG,"onprogress update. this is from thread : "+ Thread.currentThread().getName());
        // On Main Thread
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        // On Background Thread
        //onProgressUpdate(1); // updating ui
        return retrieveAsync();
    }

    private ArrayList<String> retrieveAsync(){
        Log.d(TAG,"retrieveAsync. this is from thread : "+ Thread.currentThread().getName());
        return new ArrayList<>();
    }
}
