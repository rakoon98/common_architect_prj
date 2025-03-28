package com.kun.short_time_goal.presentation.detail_goal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun DetailGoalScreen(
    idx: Int,
    viewModel: DetailGoalViewModel = hiltViewModel()
) {
    val uiState by viewModel.collectAsState()
    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            else -> {}
        }
    }

    LaunchedEffect(idx) {
        viewModel.fetchDetailGoal(idx)
    }

    Scaffold {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(color = Color.White)
        ) {
            Text(
                text = uiState.detailGoal?.content ?: "",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(Modifier.size(50.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.detailGoal?.isAlarm ?: false,
                    enabled = false,
                    onCheckedChange = {  }
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "1시간 후",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                )
            }

            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "목표 달성",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    )
                )
            }
            Spacer(Modifier.size(20.dp))
        }
    }
}