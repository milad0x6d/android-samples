package ir.milad.androidexamples.composingfunctionsandreturiningdatafromcoroutines

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun simpleAsyncAwait() = runBlocking{
    val job = launch {
        val time = measureTimeMillis {
            val r1:Deferred<Int> = async {doWorkOne()}
            val r2:Deferred<Int> = async {doWorkTwo()}
            Log.v(MainActivity.TAG, "result: ${r1.await() + r2.await()}")
        }
        Log.v(MainActivity.TAG, "Done in: $time")
    }

//    val job = launch {
//        val result = async(coroutineContext) {
//            doWork("Work 1")
//        }
//        result.await() // wait until work 1 has been finished
//        doWork("Work 2")
//    }
    job.join()

}

suspend fun doWork(msg: String):Int{
    Log.v(MainActivity.TAG, "$msg - Working")
    delay(500)
    Log.v(MainActivity.TAG, "$msg -  Work done")
    return 42
}

suspend fun doWorkOne(): Int {
    delay(100)
    Log.v(MainActivity.TAG, "Working 1")
    return Random(System.currentTimeMillis()).nextInt(42)
}

suspend fun doWorkTwo(): Int {
    delay(200)
    Log.v(MainActivity.TAG, "Working 2")
    return Random(System.currentTimeMillis()).nextInt(42)
}