package com.kun.short_time_goal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kun.short_time_goal.data.model.ScreenRoute
import com.kun.short_time_goal.internal.room.goal.GoalEntity
import com.kun.short_time_goal.presentation.add_goal.AddGoalScreen
import com.kun.short_time_goal.presentation.detail_goal.DetailGoalScreen
import com.kun.short_time_goal.presentation.main.MainScreen
import kotlinx.serialization.json.Json

@Composable
fun KunNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.MainScreenRoute.route
    ) {
        composable(ScreenRoute.MainScreenRoute.route) {
            MainScreen(
                onAddGoal = { navController.navigate(ScreenRoute.AddGoalScreenRoute.route) },
                onDetailGoal = { idx ->
                    navController.navigate(ScreenRoute.DetailGoalScreenRoute.withParam(idx))
                }
            )
        }
        composable(ScreenRoute.AddGoalScreenRoute.route) {
            AddGoalScreen()
        }
        composable(
            ScreenRoute.DetailGoalScreenRoute.route,
            arguments = listOf(navArgument("idx") { type = NavType.IntType })
        ) { backstackEntry ->
            val idx = backstackEntry.arguments?.getInt("idx")!!
            DetailGoalScreen(
                idx = idx
            )
        }
    }
}