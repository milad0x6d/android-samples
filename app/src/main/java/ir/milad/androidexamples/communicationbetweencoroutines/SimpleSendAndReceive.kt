package ir.milad.androidexamples.communicationbetweencoroutines

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun simpleSendAndReceive() = runBlocking {
    val channel = Channel<Int>()

    val job = launch {
        for (x in 1..5){
            Log.v(MainActivity.TAG, "send $x")
            channel.send(x)
        }

        channel.close()
    }

    for(y in channel){
        Log.v(MainActivity.TAG, "receive: $y")
    }

    job.join()

}