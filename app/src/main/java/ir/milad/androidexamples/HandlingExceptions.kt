package ir.milad.androidexamples

import android.util.Log
import kotlinx.coroutines.*

fun handlingExceptions() = runBlocking {
    val job = launch {
        try {
            repeat(1000){
                yield()
                Log.v(MainActivity.TAG, ".")
                Thread.sleep(10)
            }
        }catch (ex : CancellationException){
            Log.v(MainActivity.TAG, " ")
            Log.v(MainActivity.TAG, "Cancelled: ${ex.message}")
        }finally {
            run {
                Log.v(MainActivity.TAG, " ")
                Log.v(MainActivity.TAG, "Finally")
            }
        }
    }

    delay(100)
    job.cancel(CancellationException("Too many jobs"))
    job.join()

}