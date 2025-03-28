package com.kun.short_time_goal.data.repository.goal

import com.kun.short_time_goal.internal.room.goal.GoalEntity

interface GoalRepository {

    suspend fun addGoal(goalEntity: GoalEntity)
    suspend fun getGoalList(): List<GoalEntity>
    suspend fun fetchDetailGoal(idx: Int): GoalEntity

}