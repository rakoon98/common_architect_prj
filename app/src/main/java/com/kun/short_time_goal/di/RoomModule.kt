package com.kun.short_time_goal.di

import android.content.Context
import androidx.room.Room
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
object RoomModule {

    @Provides
    @Singleton
    fun goalDatabase(@ApplicationContext context: Context): GoalDatabase {
        return Room.databaseBuilder(
            context,
            GoalDatabase::class.java,
            "Goal_DB"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun goalDao(goalDatabase: GoalDatabase): GoalDao {
        return goalDatabase.goalDao()
    }
}