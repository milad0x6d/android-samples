package ir.milad.androidexamples.select

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun CoroutineScope.producer1() = produce{
    while(true){
        delay(200)
        send("from producer 1")
    }
}

fun CoroutineScope.producer2() = produce{
    while(true){
        delay(300)
        send("from producer 2")
    }
}

suspend fun selector(message1 : ReceiveChannel<String>,message2 : ReceiveChannel<String>) {
    select<Unit>{
        message1.onReceive {value ->
            Log.v(MainActivity.TAG, value)
        }
        message2.onReceive {value ->
            Log.v(MainActivity.TAG, value)
        }
    }
}

fun selectWithChannels() = runBlocking{
    val m1 = producer1()
    val m2 = producer2()

    repeat(15) {
        selector(m1,m2)
    }
}