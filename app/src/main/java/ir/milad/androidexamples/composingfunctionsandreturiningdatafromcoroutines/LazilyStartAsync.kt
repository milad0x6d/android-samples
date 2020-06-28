package ir.milad.androidexamples.composingfunctionsandreturiningdatafromcoroutines

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.*

fun lazilyStartAsync() = runBlocking {

    val job = launch {
        val result = async(start = CoroutineStart.LAZY){ doWorkLazy()}
        //never called until result.await is called
        Log.v(MainActivity.TAG, "Result is ${result.await()}")
    }

}

suspend fun doWorkLazy():Int{
    Log.v(MainActivity.TAG, "Be lazy")
    delay(500)
    Log.v(MainActivity.TAG, "Lazy done")
    return 42
}