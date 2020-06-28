package ir.milad.androidexamples.coroutinecontext

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun singleThreadContext() = runBlocking{
    newSingleThreadContext("SingleThreadContext").use { ctx ->
        val job = launch (ctx){
            Log.v(MainActivity.TAG, "SingleThreadContext : I'm working in thread " +
                    "${Thread.currentThread().name}")
        }
        job.join()
    }
}