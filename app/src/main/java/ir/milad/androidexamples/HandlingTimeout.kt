package ir.milad.androidexamples

import android.util.Log
import kotlinx.coroutines.*

fun handlingTimeout() = runBlocking {

    val job = withTimeoutOrNull(100) {
            repeat(1000){
                yield()
                Log.v(MainActivity.TAG, ".")
                Thread.sleep(10)
            }

    }

    if(job == null){
        Log.v(MainActivity.TAG, "Timeout")
    }
    delay(100)


}