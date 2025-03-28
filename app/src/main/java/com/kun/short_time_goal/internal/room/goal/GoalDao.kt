package com.kun.short_time_goal.internal.room.goal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GoalDao {

    @Insert
    suspend fun insert(goalEntity: GoalEntity)

    @Query("select * from goal_table")
    suspend fun getAllGoalList(): List<GoalEntity>

    @Query("select * from goal_table where idx = :idx")
    suspend fun fetchDetailGoal(idx: Int): GoalEntity

}