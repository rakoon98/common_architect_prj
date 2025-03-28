package com.kun.short_time_goal.presentation.main.container

import com.kun.short_time_goal.internal.room.goal.GoalEntity

data class MainUiState(
    val isLoading: Boolean = false,
    val goalList: List<GoalEntity> = emptyList()
)
