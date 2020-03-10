package ir.milad.androidexamples.jobintentservice

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService

class MyJobIntentService :JobIntentService() {

    var TAG = MyJobIntentService::class.java.simpleName
    companion object{
        fun enqueueWork(context : Context,intent:Intent){
            enqueueWork(context,MyJobIntentService::class.java,17,intent)
        }
    }
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Task Execution Started",Toast.LENGTH_SHORT).show()
    }

    //this method is similar to IntentService onHandleService method
    override fun onHandleWork(intent: Intent) {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Task Execution Finished",Toast.LENGTH_SHORT).show()
    }
}