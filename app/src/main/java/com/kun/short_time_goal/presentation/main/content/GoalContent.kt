package com.kun.short_time_goal.presentation.main.content

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kun.short_time_goal.internal.room.goal.GoalEntity

@Composable
fun GoalContent(
    goalEntity: GoalEntity,
    onClick: (GoalEntity) -> Unit
) {

    Row(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .border(
            width = 1.dp,
            color = Color.Black,
            shape = RoundedCornerShape(16.dp)
        )
        .clickable { onClick.invoke(goalEntity) }
        .padding(horizontal = 16.dp, vertical = 12.dp)) {
         Text(
             text = goalEntity.content,
             style = TextStyle(
                 fontSize = 18.sp,
                 color = Color.Magenta
             )
         )
    }

}