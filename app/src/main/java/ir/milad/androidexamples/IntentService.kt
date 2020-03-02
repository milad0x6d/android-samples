package ir.milad.androidexamples

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.lang.Exception


// provide name to your worker or background thread
class IntentService : IntentService("MyWorkerThread") {


    var TAG = ir.milad.androidexamples.IntentService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate , Thread : " + Thread.currentThread().name)
    }

    override fun onHandleIntent(p0: Intent?) {
        Log.i(TAG, "onHandleIntent , Thread : " + Thread.currentThread().name)
        var ctr = 1
        while (ctr <= 12){
            Log.i(TAG,"Time elapsed: $ctr , Thread : " + Thread.currentThread().name)
            try{
                Thread.sleep(1000)
            }catch (e : Exception){
                e.printStackTrace()
            }
            ctr++
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy , Thread : " + Thread.currentThread().name)
    }
}