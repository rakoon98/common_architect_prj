package com.kun.short_time_goal.internal.notification

import android.app.PendingIntent
import android.os.Build

object PendingIntentExt {

    fun getPendingIntentForFlag() : Int {
        return when ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ) {
            true  -> { PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE  }
            false -> { PendingIntent.FLAG_UPDATE_CURRENT }
        }
    }
    fun getCheckAlarmFlag() : Int {
        return when ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ) {
            true  -> { PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE  }
            false -> { PendingIntent.FLAG_NO_CREATE }
        }
    }
    fun getCancelFlag() : Int {
        return when ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ) {
            true  -> { PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE  }
            false -> { PendingIntent.FLAG_CANCEL_CURRENT }
        }
    }

}