package com.example.bl.common.moxy

import kotlinx.coroutines.*
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import java.util.concurrent.CancellationException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class AbstractPresenter <V : CommonView> : MvpPresenter<V>(), CoroutineScope {
    @Inject
    protected lateinit var router: Router

    protected val job = SupervisorJob()
    private val runJobs: MutableList<Job> = mutableListOf()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    internal fun launchUnit(toDo: suspend () -> Unit) {
        val job = launch { toDo() }
        runJobs.add(job)
        job.invokeOnCompletion { cause ->
            runJobs.remove(job)
            if (cause != null && cause !is CancellationException) {
                throw cause
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}