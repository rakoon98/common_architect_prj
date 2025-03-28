package com.kun.short_time_goal.presentation.detail_goal.container

import com.kun.short_time_goal.internal.room.goal.GoalEntity

data class DetailGoalUiState(
    val isLoading: Boolean = false,
    val detailGoal: GoalEntity? = null
)
