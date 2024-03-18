package com.rabeyarumi.cholokini.db.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rabeyarumi.cholokini.base.BaseFragment
import com.rabeyarumi.cholokini.R
import com.rabeyarumi.cholokini.core.DataState
import com.rabeyarumi.cholokini.databinding.FragmentLoginBinding
import com.rabeyarumi.cholokini.db.dashboard.seller.SellerDashboardActivity
import com.rabeyarumi.cholokini.isEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    val viewModel: LoginViewModel by viewModels()

      override fun setListener() {
        with(binding){

            btnLogin.setOnClickListener {

                etEmail.isEmpty()
                etPass.isEmpty()

                if (!etEmail.isEmpty() && !etPass.isEmpty())
                {
                    checkEmailPasswordValidity()
                }
            }

            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun allObserver() {
        loginObserver()
    }

    private fun loginObserver() {
        viewModel._userResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    loading.show()
                }

                is DataState.Success -> {
                    loading.dismiss()

                    startActivity(Intent(requireContext(), SellerDashboardActivity::class.java))
                    requireActivity().finish()

                }
            }
        }
    }


    private fun checkEmailPasswordValidity() {
        val emailPattern = Regex("^[a-z0-9]+@[a-z]+\\.[a-z]{2,4}$")
        val email = binding.etEmail.text.toString()
        val password = binding.etPass.text.toString()
        if (emailPattern.matches(email)){
            if (password.length>=8){
                val user = LoginUser(email,password)
                viewModel.userLogin(user)
            }
            else{
                Toast.makeText(context, "Enter correct Password", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(context, "Enter correct Email/P assword", Toast.LENGTH_SHORT).show()
        }
    }



}