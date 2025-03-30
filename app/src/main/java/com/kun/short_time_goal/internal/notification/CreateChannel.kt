package com.kun.short_time_goal.internal.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

fun createChannel(context: Context) {
    when(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        true  -> {
            val channel = NotificationChannel(
                "channel_id",
                "알림 채널",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "알림 채널"
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        false -> {}
    }
}