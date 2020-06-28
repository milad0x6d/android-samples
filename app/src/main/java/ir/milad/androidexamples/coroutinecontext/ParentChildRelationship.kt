package ir.milad.androidexamples.coroutinecontext

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.*

fun parentChildRelationship() = runBlocking {
    val outer = launch {
        launch(coroutineContext) {
            repeat(1000) {
                Log.v(MainActivity.TAG, ".")
                delay(1)
            }
        }
    }
    delay(200)
    outer.cancelChildren()
    delay(1000)
    //outer.join()
    Log.v(MainActivity.TAG, " ")
    Log.v(MainActivity.TAG, "Finished")

}