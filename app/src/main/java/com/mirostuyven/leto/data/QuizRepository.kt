package com.mirostuyven.leto.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mirostuyven.leto.network.LetoApi
import com.mirostuyven.leto.network.Quiz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepository(private val database: QuizzesDatabase) {
    val quizzes: LiveData<List<Quiz>> = Transformations.map(database.quizDao.getQuizzes()) {
        it.asDomainModel()
    }

    suspend fun refreshQuizzes() {
        withContext(Dispatchers.IO) {
            println("START REFRESH QUIZZES")
            val quizzes = LetoApi.service.listQuizzes().data
            database.quizDao.insertAll(quizzes.asDatabaseModel())
            println("FINISH REFRESH QUIZZES")
        }
    }
}