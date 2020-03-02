package ir.milad.androidexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartBackgroundService.setOnClickListener {
            val intent = Intent(this,BackgroundService::class.java)
            intent.putExtra("","")
            startService(intent)

        }

        btnStopBackgroundService.setOnClickListener {
            val intent = Intent(this,BackgroundService::class.java)
            stopService(intent)
        }

        btnStartIntentService.setOnClickListener {
            val intent = Intent(this,IntentService::class.java)
            startService(intent)
        }
    }

}
