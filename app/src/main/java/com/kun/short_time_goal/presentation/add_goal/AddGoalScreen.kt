package com.kun.short_time_goal.presentation.add_goal

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.WorkManager
import com.kun.short_time_goal.internal.notification.showNotification
import com.kun.short_time_goal.internal.worker.goalWorkerRequest
import com.kun.short_time_goal.presentation.add_goal.container.AddGoalSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AddGoalScreen(
    viewModel: AddGoalViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    workManager: WorkManager = WorkManager.getInstance(context)
) {
    val uiState by viewModel.collectAsState()
    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            AddGoalSideEffect.OnAddGoal -> {
                goalWorkerRequest(workManager)
            }
            else -> {}
        }
    }

    var content by remember { mutableStateOf("") }
    var isAlarm by remember { mutableStateOf(false) }

    val permission: String? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.POST_NOTIFICATIONS
    else
        null

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            isAlarm = !isAlarm
            Toast.makeText(context, "알람 권한이 설정 되었습니다.", Toast.LENGTH_SHORT).show()
            showNotification(context)
        } else {
            Toast.makeText(context, "알람 권한이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(color = Color.White)
        ) {
            OutlinedTextField(
                value = content,
                onValueChange = {
                    content = it
                },
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
                    checked = isAlarm,
                    onCheckedChange = { isAlarm = !isAlarm }
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isAlarm,
                    onCheckedChange = {
                        val changeBool = !isAlarm
                        permission?.let {
                            permissionLauncher.launch(permission)
                        } ?: run {
                            isAlarm = changeBool
                        }
                    }
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "알림 띄우기 여부",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                )
            }
            Button(
                onClick = {
                    viewModel.addGoal(
                        content = content,
                        addTimeHour = 1,
                        isAlarm = isAlarm
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(8.dp)) {
                Text(
                    text = "목표 추가",
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