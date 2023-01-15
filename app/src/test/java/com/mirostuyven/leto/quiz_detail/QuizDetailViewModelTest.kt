package com.mirostuyven.leto.quiz_detail

import com.mirostuyven.leto.getOrAwaitValue
import org.junit.Test
import java.util.concurrent.TimeUnit

class QuizDetailViewModelTest {
    @Test
    fun getQuiz() {
        val quizId = "ieukd5koDXM"
        val viewModel = QuizDetailViewModel(quizId)
        val quiz = viewModel.quiz.getOrAwaitValue(1, TimeUnit.MINUTES)
        assert(quiz?.id == quizId)
        assert(quiz?.title == "Trivia")
        assert(quiz?.creator?.name == "Alice")
    }
}