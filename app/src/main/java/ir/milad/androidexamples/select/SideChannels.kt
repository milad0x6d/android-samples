package ir.milad.androidexamples.select

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun CoroutineScope.sideProducer() = produce{
    var i = 0
    while (true) {
        delay(5000)
        send(i++)
    }
}

fun SideChannels() = runBlocking {
    var msg = sideProducer()
    select<Unit>{
        msg.onReceive {value ->
            Log.v(MainActivity.TAG, value.toString())
        }
        onTimeout(6000) {
            Log.v(MainActivity.TAG, "Time out")
        }
    }
}