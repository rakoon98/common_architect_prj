package com.kun.short_time_goal.presentation.main

import androidx.lifecycle.ViewModel
import com.kun.short_time_goal.presentation.main.container.MainSideEffect
import com.kun.short_time_goal.presentation.main.container.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel(), ContainerHost<MainUiState, MainSideEffect> {
    override val container: Container<MainUiState, MainSideEffect> = container(MainUiState())
}