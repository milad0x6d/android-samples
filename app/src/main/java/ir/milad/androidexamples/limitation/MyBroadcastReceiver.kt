package ir.milad.androidexamples.limitation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ir.milad.androidexamples.IntentService
import ir.milad.androidexamples.jobintentservice.MyJobIntentService

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        //when application boot up and if device is
        // oreo or higher application will crash
//        if(p1?.action.equals(Intent.ACTION_BOOT_COMPLETED)){
//            val intent = Intent(context,IntentService::class.java)
//            intent.putExtra("sleepTime",12)
//            context?.startService(intent)
//        }


        if (p1?.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            val intent = Intent(context, MyJobIntentService::class.java)
            intent.putExtra("sleepTime", 5)
            MyJobIntentService.enqueueWork(context!!,intent)
        }
    }
}