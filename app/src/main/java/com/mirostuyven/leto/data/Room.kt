package com.mirostuyven.leto.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuizDao {
    @Query("select * from databasequiz")
    fun getQuizzes(): LiveData<List<DatabaseQuiz>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<DatabaseQuiz>)
}

@Database(entities = [DatabaseQuiz::class], version = 1)
abstract class LetoDatabase : RoomDatabase() {
    abstract val quizDao: QuizDao
}

private lateinit var INSTANCE: LetoDatabase

fun getDatabase(context: Context): LetoDatabase {
    synchronized(LetoDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                LetoDatabase::class.java,
                "leto"
            ).build()
        }
    }
    return INSTANCE
}