package com.kun.short_time_goal.presentation.detail_goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kun.short_time_goal.data.repository.goal.GoalRepository
import com.kun.short_time_goal.presentation.detail_goal.container.DetailGoalSideEffect
import com.kun.short_time_goal.presentation.detail_goal.container.DetailGoalUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailGoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
): ViewModel(), ContainerHost<DetailGoalUiState, DetailGoalSideEffect>  {
    override val container: Container<DetailGoalUiState, DetailGoalSideEffect> = container(
        DetailGoalUiState()
    )

    fun fetchDetailGoal(idx: Int) = viewModelScope.launch {
        intent {
            reduce { state.copy(isLoading = true) }

            val goal = goalRepository.fetchDetailGoal(idx)
            reduce { state.copy(isLoading = false, detailGoal = goal) }
        }
    }

}