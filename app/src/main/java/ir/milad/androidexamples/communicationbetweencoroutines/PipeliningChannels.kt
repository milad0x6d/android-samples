package ir.milad.androidexamples.communicationbetweencoroutines

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.pipeliningProduceNumbers() = produce {
    var x = 1
    while(true){
        send(x++)
    }
}
fun CoroutineScope.squareNumbers(numbers : ReceiveChannel<Int>) = produce {
    for (x in numbers) send(x * x)
}

fun pipeliningChannels() = runBlocking {
    val producer = pipeliningProduceNumbers()
    val square = squareNumbers(producer)

    for (i in 1..5) Log.v(MainActivity.TAG, square.receive().toString())

    Log.v(MainActivity.TAG, "Main done")

    square.cancel()
    producer.cancel()



}