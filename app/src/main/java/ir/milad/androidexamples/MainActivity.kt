package ir.milad.androidexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStartService.setOnClickListener {
            startService()
        }

        buttonStopService.setOnClickListener {
            stopService()
        }

    }

    fun startService(){
        val serviceIntent = Intent(this,ForgroundService::class.java)
        serviceIntent.putExtra("inputExtra","Foreground Service Title")
        ContextCompat.startForegroundService(this,serviceIntent)
    }

    fun stopService(){
        val serviceIntent = Intent(this,ForgroundService::class.java)
        stopService(serviceIntent)
    }
}
