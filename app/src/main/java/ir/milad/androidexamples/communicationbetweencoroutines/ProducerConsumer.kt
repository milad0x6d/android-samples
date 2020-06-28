package ir.milad.androidexamples.communicationbetweencoroutines

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers() = produce {
    for (x in 1..5) {
        Log.v(MainActivity.TAG, "send $x")
        send(x)
    }

    Log.v(MainActivity.TAG, "Done")
}


fun producerConsumer() = runBlocking{
    val channel = produceNumbers()

    channel.consumeEach {
        Log.v(MainActivity.TAG, it.toString())
    }
}