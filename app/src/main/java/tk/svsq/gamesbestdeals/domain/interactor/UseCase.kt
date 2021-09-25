package tk.svsq.gamesbestdeals.domain.interactor

import androidx.annotation.WorkerThread
import kotlinx.coroutines.*
import timber.log.Timber

abstract class UseCase<Params, out Type> where Type: Any {

    // dispatchers
    private val UI = Dispatchers.Main
    private val BG = Dispatchers.IO

    // type for empty parameter
    class None

    // parameters
    protected var params: Params? = null

    private var job : Deferred<Result<Type>>? = null

    fun withParams(params: Params) {
        this.params = params
    }

    // run

    @WorkerThread
    abstract suspend fun run() : Result<Type>

    @WorkerThread
    suspend fun runWithParams(params: Params) : Result<Type> {
        withParams(params)
        return run()
    }

    // invoke

    operator fun invoke(params: Params, onResult: (Result<Type>) -> Unit = {}) {
        withParams(params)
        job = CoroutineScope(BG).async { run() }
        CoroutineScope(UI).launch {
            val r = job!!.await()
            if (isActive) onResult(r)
        }
    }

    operator fun invoke(onResult: (Result<Type>) -> Unit = {}) {
        job = CoroutineScope(BG).async { run() }
        CoroutineScope(UI).launch {
            val r = job!!.await()
            if (isActive) onResult(r)
        }
    }

    fun isActive() = job?.isActive ?: false.also { Timber.w("job is null") }
    fun isCancelled() = job?.isCancelled ?: false.also { Timber.w("job is null") }
    fun isCompleted() = job?.isCompleted ?: false.also { Timber.w("job is null") }

    // invoke + suspend

    suspend operator fun invoke(params: Params) : Result<Type> {
        withParams(params)
        return run()
    }

    suspend operator fun invoke(): Result<Type> {
        return run()
    }

}