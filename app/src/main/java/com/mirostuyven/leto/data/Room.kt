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
abstract class QuizzesDatabase : RoomDatabase() {
    abstract val quizDao: QuizDao
}

private lateinit var INSTANCE: QuizzesDatabase

fun getDatabase(context: Context): QuizzesDatabase {
    synchronized(QuizzesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                QuizzesDatabase::class.java,
                "quizzes"
            ).build()
        }
    }
    return INSTANCE
}