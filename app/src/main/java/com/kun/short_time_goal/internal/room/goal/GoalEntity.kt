package com.kun.short_time_goal.internal.room.goal

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "goal_table")
@Serializable
data class GoalEntity(
     val createdAt: String = "", // yyyyMMddHHmmss -> toLocalDate
     val goalAt: String = "", // yyyyMMddHHmmss -> toLocalDate
     val content: String = "",
     val isAlarm: Boolean = false,
     val isGoal: Boolean = false
) {

    @PrimaryKey(autoGenerate = true) var idx: Int = 0
}
