package com.kun.short_time_goal.internal.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kun.short_time_goal.R
import com.kun.short_time_goal.presentation.MainActivity

fun showNotification(context: Context) {
    createChannel(context)

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
    }
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntentExt.getPendingIntentForFlag())

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.ic_launcher_foreground) // 알림 아이콘
        .setContentTitle("테스트 알림")
        .setContentText("단기 목표앱 알림바 테스트")
        .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
        .setAutoCancel(false)
        .setVibrate(longArrayOf(2000, 1000,2000, 1000,2000, 1000,2000, 1000,2000, 1000,2000, 1000,))
        .setFullScreenIntent(pendingIntent, true)
        .build()




    notificationManager.notify(1, notification) // ID를 1로 설정하여 알림 표시
}