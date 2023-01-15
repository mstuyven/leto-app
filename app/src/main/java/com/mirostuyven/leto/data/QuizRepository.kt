package com.mirostuyven.leto.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mirostuyven.leto.network.LetoApi
import com.mirostuyven.leto.network.Quiz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepository(private val database: LetoDatabase) {
    val quizzes: LiveData<List<Quiz>> = Transformations.map(database.quizDao.getQuizzes()) {
        it.asDomainModel()
    }

    suspend fun refreshQuizzes() {
        withContext(Dispatchers.IO) {
            val quizzes = LetoApi.service.listQuizzes().data
            database.quizDao.insertAll(quizzes.asDatabaseModel())
        }
    }
}