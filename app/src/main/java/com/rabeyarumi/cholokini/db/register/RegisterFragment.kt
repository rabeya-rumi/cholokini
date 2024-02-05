package com.rabeyarumi.cholokini.db.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rabeyarumi.cholokini.R
import com.rabeyarumi.cholokini.databinding.FragmentRegisterBinding
import com.rabeyarumi.cholokini.isEmpty


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(inflater,container,false)


        setListener()


        return binding.root
    }


    private fun setListener() {
         with(binding){
             btnRegister.setOnClickListener {
                 etName.isEmpty()
                 etEmail.isEmpty()
                 etPass.isEmpty()

                 if (!etName.isEmpty() && !etEmail.isEmpty() && !etPass.isEmpty())
                 {
                     checkEmailPasswordValidity()
                 }
             }

             btnLogin.setOnClickListener {
                 findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

             }
         }
    }

    private fun checkEmailPasswordValidity() {
        val emailPattern = Regex("^[a-z0-9]+@[a-z]+\\.[a-z]{2,4}$")
        val email = binding.etEmail.editableText.toString()
        val password = binding.etPass.editableText.toString()
        if (emailPattern.matches(email)){
            if (password.length>=8){
                findNavController().navigate(R.id.action_registerFragment_to_dashboardFragment)
            }
            else{
                Toast.makeText(context, "Enter correct Password", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(context, "Enter correct Email/Password", Toast.LENGTH_SHORT).show()
        }
    }



}