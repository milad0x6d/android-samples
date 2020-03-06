package ir.milad.androidexamples

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log
import java.lang.Exception

class BackgroundService : Service() {

    val TAG = BackgroundService::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        //START_STICKY : Service restarts automatically
        //               Intent is lost ( ata becomes null )

        //START_REDELIVER_INTENT : Service restarts automatically
        //                         Intent redelivered

        //START_NOT_STICKY : Service not restarted
        //                   Intent is lost ( becomes null )

        val duration = intent?.getIntExtra("sleepTime",12) ?: 12

        MyAsyncTask().execute()

        return START_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        Log.i(TAG, "onBind")
        return null
    }


    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }

    class MyAsyncTask : AsyncTask<Void, String, Void>() {
        val TAG = BackgroundService::class.java.simpleName
        override fun onPreExecute() {
            super.onPreExecute()
            //perform operation in main thread
            Log.i(TAG, "onPreExecute")
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            //perform tasks in background or worker thread
            Log.i(TAG, "doInBackground")

            var ctr = 1
            while (ctr <= 12){
                publishProgress("Time elapsed: $ctr")
                try{
                    Thread.sleep(1000)
                }catch (e : Exception){
                    e.printStackTrace()
                }
                ctr++
            }

            return null
        }

        override fun onProgressUpdate(vararg values: String?) {
            //perform operation in main thread
            Log.i(TAG, "onProgressUpdate : $values")
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            //perform operation in main thread
            Log.i(TAG, "onPostExecute")
            super.onPostExecute(result)
        }

    }
}