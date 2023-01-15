package com.mirostuyven.leto.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mirostuyven.leto.R
import com.mirostuyven.leto.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val model: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = model
        binding.lifecycleOwner = this
        binding.emailInput.editText?.addTextChangedListener {
            model.email.value = it.toString()
        }
        binding.passwordInput.editText?.addTextChangedListener {
            model.password.value = it.toString()
        }
        binding.loginButton.setOnClickListener {
            binding.loginProgress.show()
            model.doLogin { token ->
                binding.loginProgress.hide()
                if (token != null) {
                    findNavController().popBackStack()
                } else {

                }
            }
        }
        return binding.root
    }

}