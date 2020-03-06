package ir.milad.androidexamples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartBackgroundService.setOnClickListener {
            val intent = Intent(this,BackgroundService::class.java)
            intent.putExtra("sleepTime",12)
            startService(intent)

        }

        btnStopBackgroundService.setOnClickListener {
            val intent = Intent(this,BackgroundService::class.java)
            stopService(intent)
        }

        btnStartIntentService.setOnClickListener {
            val intent = Intent(this,IntentService::class.java)
            intent.putExtra("sleepTime",5)
            startService(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("my.own.broadcast")
        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBroadcastReceiver,intentFilter)
    }

    private val  myLocalBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, localIntent: Intent?) {
            val result = localIntent?.getIntExtra("result",-1)
            txtResult.text = result.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalBroadcastReceiver)
    }

}

/*Started Service (background service)
    Runs in main thread or ui thread
    by default it can perform short operations
    may block the ui
    multi-threading needs to be handled
    call stopService() or stopSelf() to stop the service
 */

/*Intent Service (Advanced Background Service)
    Runs in worker or background thread
    performs short and long operations
    does not block the ui
    multi-threading handles internally. it performs one task at a time (mor task go to queue)
    stops automatically when task is over
 */