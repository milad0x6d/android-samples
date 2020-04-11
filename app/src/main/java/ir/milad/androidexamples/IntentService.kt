package ir.milad.androidexamples

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.Exception

// intent service is subclass of background service and is advance form of background service
// no need to call stopSelf() or stopService() method to stop intent service this is automatically
// executes one task at a time
    // if you assign multiple tasks to the intent service
        // - then the task will be arranged tin the Work Queue and one task will be executed at a time
        // - Handles multi-threading internally and saves memory

// provide name to your worker or background thread
class IntentService : IntentService("MyWorkerThread") {


    var TAG = ir.milad.androidexamples.IntentService::class.java.simpleName

    // onStartCommand is optional to override
        // - by default it is implemented internally
        // - It receives intent (data) to execute tasks and
            // sends one intent at a time to onHandleIntent() method


    // optional to override
    // Thread : main
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate , Thread : " + Thread.currentThread().name)
    }


    // force to override
    // Thread : worker or background
    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, "onHandleIntent , Thread : " + Thread.currentThread().name)

        val duration = intent?.getIntExtra("sleepTime",-12) ?: 12

        var ctr = 1
        while (ctr <= duration){
            Log.i(TAG,"Time elapsed: $ctr , Thread : " + Thread.currentThread().name)
            try{
                Thread.sleep(1000)
            }catch (e : Exception){
                e.printStackTrace()
            }
            ctr++
        }

        val localIntent = Intent("my.own.broadcast")
        localIntent.putExtra("result",ctr)
        //sendBroadcast(localIntent) this will send to out side application too
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent)//send broadcast just within application
    }

    // optional to override
    // Thread : main
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy , Thread : " + Thread.currentThread().name)
    }
}