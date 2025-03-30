package com.kun.short_time_goal.internal.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.kun.short_time_goal.R

fun showNotification(context: Context) {
    createChannel(context)

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.ic_launcher_foreground) // 알림 아이콘
        .setContentTitle("테스트 알림")
        .setContentText("Jetpack Compose에서 알림을 생성했습니다!")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .build()

    notificationManager.notify(1, notification) // ID를 1로 설정하여 알림 표시
}