package com.kun.short_time_goal.data.repository.goal

import com.kun.short_time_goal.internal.room.goal.GoalDao
import com.kun.short_time_goal.internal.room.goal.GoalEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalRepositoryImpl @Inject constructor(
    private val goalDao: GoalDao
): GoalRepository {

    override suspend fun addGoal(goalEntity: GoalEntity) {
        goalDao.insert(goalEntity)
    }

    override suspend fun getGoalList(): List<GoalEntity> {
        return goalDao.getAllGoalList()
    }

    override suspend fun fetchDetailGoal(idx: Int): GoalEntity {
        return goalDao.fetchDetailGoal(idx)
    }

}