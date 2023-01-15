package com.mirostuyven.leto.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
            findNavController().navigate(HomeFragmentDirections.actionJoinGame())
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
        val preferences = context?.getSharedPreferences("leto", Context.MODE_PRIVATE)
        val authToken = preferences?.getString("auth_token", null)
        if (authToken == null) {
            findNavController().navigate(HomeFragmentDirections.actionStartLogin())
        } else {
            model.loadUser(authToken)
        }
        model.user.observe(viewLifecycleOwner) {
            binding.welcomeText.text = getString(R.string.welcome, it.name)
        }
        binding.logoutButton.setOnClickListener {
            if (preferences?.edit()?.remove("auth_token")?.commit() == true) {
                findNavController().navigate(HomeFragmentDirections.actionStartLogin())
            }
        }
        return binding.root
    }
}