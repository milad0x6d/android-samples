package ir.milad.androidexamples

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun joining() = runBlocking{

    val job = launch {
        delay(1000)
        Log.v(MainActivity.TAG, "world")
    }

    Log.v(MainActivity.TAG, "hello")
    job.join()
}