package ir.milad.androidexamples.coroutinecontext

import android.util.Log
import ir.milad.androidexamples.MainActivity
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//CommonPool
//The fork/join pool, which is the default pool in the current implementation

//CoroutineContext
//Inherit the context of the current coroutine

//DefaultDispatcher
//The fork/join pool, which is the default pool in the current implementation

//newSingleThreadContext
//Runs the coroutine on new thread. This is an expensive operation. The new thread needs to be managed by your code.

//Unconfined
//Starts the coroutine in the calling thread but only until the first suspension point.
//Resumes on thread determined by the suspending function.

fun coroutineContext() = runBlocking {

    val jobs = arrayListOf<Job>()
    jobs += launch(coroutineContext) {
        //context of the parent, runBlocking coroutine
        Log.v(
            MainActivity.TAG,
            "coroutineContext: I'm working in thread ${Thread.currentThread().name}"
        )
        delay(100)
        Log.v(
            MainActivity.TAG,
            "coroutineContext: After delay in thread ${Thread.currentThread().name}"
        )
    }

    jobs.forEach {
        it.join()
    }
}

fun unconfinedContext() = runBlocking {

    val jobs = arrayListOf<Job>()
    jobs += launch(Unconfined) {
        Log.v(
            MainActivity.TAG,
            "coroutineContext: I'm working in thread ${Thread.currentThread().name}"
        )
        delay(100)
        //yield() // after yield coroutine still is on main thread
        Log.v(
            MainActivity.TAG,
            "coroutineContext: After delay in thread ${Thread.currentThread().name}"
        )
    }

    jobs.forEach {
        it.join()
    }
}

