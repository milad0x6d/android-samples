package ir.milad.androidexamples

import android.util.Log
import kotlinx.coroutines.*

fun cancellationCooperative() = runBlocking{

    val job = launch {
        repeat(1000){
            // check for cancellation
            //if(!isActive) throw CancellationException()
            //if(!isActive) return@launch
            Log.v(MainActivity.TAG, ".")
            //yield() // check for cancellation
            Thread.sleep(1)
        }
    }

    delay(100)
    job.cancelAndJoin()
    Log.v(MainActivity.TAG, "done")

}