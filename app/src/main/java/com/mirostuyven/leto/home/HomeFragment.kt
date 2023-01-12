package com.mirostuyven.leto.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.mirostuyven.leto.R
import com.mirostuyven.leto.databinding.FragmentHomeBinding
import com.mirostuyven.leto.network.Quiz

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
        binding.lifecycleOwner = this
        binding.viewModel = model
        binding.joinGame.setOnClickListener {
            it.findNavController().navigate(R.id.action_joinGame)
        }
        binding.quizzesList.adapter = QuizListAdapter()
        return binding.root
    }
}