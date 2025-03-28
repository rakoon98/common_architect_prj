package com.kun.short_time_goal.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kun.short_time_goal.presentation.main.content.GoalContent
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onAddGoal: () -> Unit,
    onDetailGoal: (idx: Int) -> Unit,
) {
    val uiState by viewModel.collectAsState()
    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAllGoalList()
    }

    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.White),
                onClick = { onAddGoal.invoke() }
            ) {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape),
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        },
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(color = Color.White)) {
            when(uiState.goalList.isNotEmpty()) {
                true  -> {
                    Column(modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(color = Color.White),
                        verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        repeat(uiState.goalList.size) { i ->
                            GoalContent(
                                goalEntity = uiState.goalList[i],
                                onClick = { entity -> onDetailGoal.invoke(entity.idx) }
                                )
                        }
                    }
                }
                false -> {
                    Box(Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Text(
                            text = "목표가 없습니다!",
                            style = TextStyle(
                                fontSize = 24.sp,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}