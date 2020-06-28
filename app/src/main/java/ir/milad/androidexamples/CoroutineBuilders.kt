package ir.milad.androidexamples

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun builder() = runBlocking {

    launch{
        delay(1000)
        Log.v(MainActivity.TAG, "world")
    }

    Log.v(MainActivity.TAG, "Hello")

    doWork()

}

suspend fun doWork(){
    // do some work
    delay(2000)
}