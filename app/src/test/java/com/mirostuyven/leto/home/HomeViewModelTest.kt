package com.mirostuyven.leto.home

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mirostuyven.leto.getOrAwaitValue
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    @Test
    fun refreshQuizzes() {
        val homeViewModel = HomeViewModel(ApplicationProvider.getApplicationContext())
        val quizzes = homeViewModel.quizzes.getOrAwaitValue(1, TimeUnit.MINUTES)
        assert(quizzes.size == 2)
    }
}