package com.kun.short_time_goal.presentation.add_goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kun.short_time_goal.common.DateUtil.toTimeFormat
import com.kun.short_time_goal.data.repository.goal.GoalRepository
import com.kun.short_time_goal.internal.room.goal.GoalEntity
import com.kun.short_time_goal.presentation.add_goal.container.AddGoalSideEffect
import com.kun.short_time_goal.presentation.add_goal.container.AddGoalUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddGoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository
): ViewModel(), ContainerHost<AddGoalUiState, AddGoalSideEffect> {
    override val container: Container<AddGoalUiState, AddGoalSideEffect> = container(AddGoalUiState())

    fun addGoal(
        content: String,
        addTimeHour: Int, // n 시간 후
        isAlarm: Boolean
    ) = viewModelScope.launch {
        val addCreatedAtTimeFormatString = LocalDateTime.now().toTimeFormat()
        val addGoalTimeFormatString = LocalDateTime.now().apply {
            plusHours(addTimeHour.toLong())
        }.toTimeFormat()

        val goalEntity = GoalEntity(
            createdAt = addCreatedAtTimeFormatString,
            goalAt = addGoalTimeFormatString,
            content = content,
            isAlarm = isAlarm
        )

        goalRepository.addGoal(goalEntity)

        intent {
            postSideEffect(AddGoalSideEffect.OnAddGoal)
        }
    }
}