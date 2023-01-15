package com.mirostuyven.leto.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mirostuyven.leto.R
import com.mirostuyven.leto.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val model: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = model
        binding.lifecycleOwner = this
        binding.joinGame.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionStartLogin())
        }
        binding.quizzesList.adapter = QuizListAdapter { quiz ->
            findNavController().navigate(HomeFragmentDirections.actionViewQuizDetail(quiz.id))
        }
        model.quizzesLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.quizzesProgress.show()
            } else {
                binding.quizzesProgress.hide()
            }
        }
        return binding.root
    }
}