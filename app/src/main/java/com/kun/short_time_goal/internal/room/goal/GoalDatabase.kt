package com.kun.short_time_goal.internal.room.goal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GoalEntity::class], version = 1, exportSchema = false)
abstract class GoalDatabase: RoomDatabase() {
    abstract fun goalDao() : GoalDao
}