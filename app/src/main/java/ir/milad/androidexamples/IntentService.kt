package ir.milad.androidexamples

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.lang.Exception


// provide name to your worker or background thread
class IntentService : IntentService("MyWorkerThread") {


    var TAG = ir.milad.androidexamples.IntentService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate , Thread : " + Thread.currentThread().name)
    }

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

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy , Thread : " + Thread.currentThread().name)
    }
}