package ir.milad.androidexamples

import android.util.Log
import ir.milad.androidexamples.MainActivity.Companion.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() = runBlocking{

    val result = AtomicInteger()

    for(i in 1..1_500_00){
        // uses 100% of cpu
//        thread (start = true){
//            result.getAndIncrement()
//        }
        launch {
            result.getAndIncrement()
            Log.v(TAG, result.get().toString())
        }
    }

    Thread.sleep(1000)
    Log.v(TAG, result.get().toString())





    //coroutine
//    launch{
//        delay(1000)
//        Log.v(TAG, "World")
//    }
//
//    Log.v(TAG, "Hello")
//    Thread.sleep(1000)


    //Traditional
//    thread {
//        Thread.sleep(1000)
//        Log.v(TAG,"World")
//    }
//
//    Log.v(TAG,"Hello, ")
//    Thread.sleep(1500)
}