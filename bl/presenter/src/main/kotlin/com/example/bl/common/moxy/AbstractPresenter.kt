package com.example.bl.common.moxy

import com.example.bl.common.moxy.view.CommonView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import java.util.concurrent.CancellationException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class AbstractPresenter<V : CommonView> : MvpPresenter<V>(), CoroutineScope {

    @Inject
    lateinit var uiContext: CoroutineDispatcher

    @Inject
    protected lateinit var router: Router

    protected val job = SupervisorJob()
    private val runUiJobs: MutableList<Job> = mutableListOf()
    private val runIOJobs: MutableList<Job> = mutableListOf()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    internal fun launchUnit(toDo: suspend () -> Unit) {
        val job = launch { toDo.invoke() }
        runUiJobs.add(job)
        job.invokeOnCompletion { throwable ->
            runUiJobs.remove(job)
            if (throwable != null && throwable !is CancellationException) {
                throw throwable
            }
        }
    }

    internal fun launchOnIO(todo: suspend () -> Unit) {
        val job = CoroutineScope(IO).launch { todo.invoke() }
        runIOJobs.add(job)
        job.invokeOnCompletion { throwable ->
            runIOJobs.remove(job)
            if (throwable != null && throwable !is CancellationException) {
                throw throwable
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }

    fun onBackPressed() {
        router.exit()
    }
}