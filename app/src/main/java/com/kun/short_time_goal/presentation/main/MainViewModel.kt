package com.kun.short_time_goal.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kun.short_time_goal.data.repository.goal.GoalRepository
import com.kun.short_time_goal.presentation.main.container.MainSideEffect
import com.kun.short_time_goal.presentation.main.container.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val goalRepository: GoalRepository
): ViewModel(), ContainerHost<MainUiState, MainSideEffect> {
    override val container: Container<MainUiState, MainSideEffect> = container(MainUiState())

    fun getAllGoalList() = viewModelScope.launch {
        intent {
            reduce { state.copy(isLoading = true) }

            val goalList = goalRepository.getGoalList()

            reduce { state.copy(isLoading = false, goalList = goalList) }
        }
    }
}
