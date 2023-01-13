package com.mirostuyven.leto.quiz_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mirostuyven.leto.R
import com.mirostuyven.leto.databinding.FragmentHomeBinding
import com.mirostuyven.leto.databinding.FragmentQuizDetailBinding
import com.mirostuyven.leto.home.HomeViewModel
import com.mirostuyven.leto.home.QuizListAdapter

class QuizDetailFragment : Fragment() {
    private var model: QuizDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val quizId = arguments?.getString("quizId")
        val viewModel: QuizDetailViewModel by viewModels { QuizDetailViewModelFactory(quizId) }
        model = viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentQuizDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_detail, container, false)
        binding.viewModel = model
        binding.lifecycleOwner = this
        binding.startQuiz.setOnClickListener {
            println("START QUIZ!")
        }
        return binding.root
    }
}