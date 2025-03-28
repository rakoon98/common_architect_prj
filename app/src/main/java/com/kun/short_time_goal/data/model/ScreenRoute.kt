package com.kun.short_time_goal.data.model

sealed class ScreenRoute(val route: String) {
    data object MainScreenRoute: ScreenRoute("main")
    data object AddGoalScreenRoute: ScreenRoute("add_goal")
    data object DetailGoalScreenRoute: ScreenRoute("detail_goal/{idx}") {
        fun withParam(idx: Int): String = "detail_goal/${idx}"
    }
}