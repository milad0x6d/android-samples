package ir.milad.androidexamples.communicationbetweencoroutines

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun bufferedChannels() = runBlocking{

    val channel = Channel<Int>(4) // create buffered channel
    val sender = launch(coroutineContext) {
        // launch sender coroutine
        repeat(10) {
            Log.v(MainActivity.TAG, "sending $it") // print before sending each element
            channel.send(it) // will suspend when buffer is full
        }
    }
    // don't receive anything.. just wait...
    delay(1000)
    launch {
        repeat(10) {
            Log.v(MainActivity.TAG, " --Receiving ${channel.receive()}")
            sender.cancel() // cancel sender coroutine
        }
    }



}

