package ir.milad.androidexamples

import android.util.Log
import kotlinx.coroutines.*

fun cancellingCoroutine() = runBlocking {

    val job = launch {
        repeat(1000){
            delay(100)
            Log.v(MainActivity.TAG, ".")
        }
    }

    delay(2500)

    //job.cancel()
    //job.join()

    job.cancelAndJoin()

    Log.v(MainActivity.TAG, "done")

}