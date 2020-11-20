package ir.milad.androidexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ir.milad.androidexamples.pref.MigrateToPrefDataStoreActivity
import ir.milad.androidexamples.pref.PrefDataStoreActivity
import ir.milad.androidexamples.proto.ProtoDataStoreActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnPref).setOnClickListener {
            startActivity(Intent(this, PrefDataStoreActivity::class.java))
        }

        findViewById<View>(R.id.btnProto).setOnClickListener {
            startActivity(Intent(this, ProtoDataStoreActivity::class.java))
        }

        findViewById<View>(R.id.btnMigratePref).setOnClickListener {
            startActivity(Intent(this, MigrateToPrefDataStoreActivity::class.java))
        }
    }
}