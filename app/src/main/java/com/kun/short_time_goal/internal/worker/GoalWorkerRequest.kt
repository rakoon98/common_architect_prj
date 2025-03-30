package com.kun.short_time_goal.internal.worker

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * workManager -> WorkManager.getInstance(context)
 */
fun goalWorkerRequest(workManager: WorkManager) {
    val request = OneTimeWorkRequestBuilder<GoalWorker>()
        .setInitialDelay(1, TimeUnit.HOURS)
        .build()

    workManager.enqueue(request)
}