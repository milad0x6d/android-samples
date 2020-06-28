package ir.milad.androidexamples

import android.util.Log
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

suspend fun actorRun(context:CoroutineContext,numberOfJobs : Int,
                     count:Int , action :suspend() ->Unit) : Long = runBlocking {
    //action is repeated by each coroutine
    return@runBlocking measureTimeMillis {
        val jobs = List(numberOfJobs){
            launch {
                repeat(count){
                    action()
                }
            }
        }
        jobs.forEach { it.join()}
    }
}

sealed class CounterMsg
object InitCounter : CounterMsg()
object IncCounter : CounterMsg()
class GetCounter ( val response : CompletableDeferred<Int>) : CounterMsg()

fun CoroutineScope.counterActor() = actor<CounterMsg>{
    var counter = 0
    for(msg in channel){
        when(msg){
            is InitCounter -> counter = 0
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

fun actorMain() = runBlocking{
    val jobs = 100 // reduce number of jobs from 1000 to 100 will make faster
    val count = 1000

    val counter = counterActor()

    counter.send(InitCounter)

    val time = actorRun(coroutineContext,jobs,count){
        counter.send(IncCounter)
    }

    var response = CompletableDeferred<Int>()
    counter.send(GetCounter(response))

    Log.v(MainActivity.TAG, "result is ${response.await()}")
    Log.v(MainActivity.TAG, "Completed ${jobs * count} actions is $time ms")
}