package com.kun.short_time_goal.di

import android.content.Context
import androidx.room.Room
import com.kun.short_time_goal.data.repository.goal.GoalRepository
import com.kun.short_time_goal.data.repository.goal.GoalRepositoryImpl
import com.kun.short_time_goal.internal.room.goal.GoalDao
import com.kun.short_time_goal.internal.room.goal.GoalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGoalRepository(goalDao: GoalDao): GoalRepository {
        return GoalRepositoryImpl(goalDao)
    }

}