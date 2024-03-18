package com.rabeyarumi.cholokini.db.register


import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rabeyarumi.cholokini.base.BaseFragment
import com.rabeyarumi.cholokini.R
import com.rabeyarumi.cholokini.core.DataState
import com.rabeyarumi.cholokini.databinding.FragmentRegisterBinding
import com.rabeyarumi.cholokini.db.dashboard.seller.SellerDashboardActivity
import com.rabeyarumi.cholokini.isEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    val viewModel : RegistrationViewModel by viewModels()

    override fun setListener() {
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

    override fun allObserver() {
        registrationObserver()
    }

    private fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error -> {

                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading ->  {

                    loading.show()
                    Toast.makeText(context, "Loading.....", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success ->  {

                    loading.dismiss()

                    startActivity(Intent(requireContext(), SellerDashboardActivity::class.java))
                    requireActivity().finish()
                    //Toast.makeText(context, "created user : ${it.data}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


    private fun checkEmailPasswordValidity() {
        val emailPattern = Regex("^[a-z0-9]+@[a-z]+\\.[a-z]{2,4}$")
        val name = binding.etName.text.toString()
        val email = binding.etEmail.editableText.toString()
        val password = binding.etPass.editableText.toString()
        if (emailPattern.matches(email)){
            if (password.length>=8){

                val user = User(
                    name,
                    email,
                    password,
                    "Seller",
                    ""
                )
                viewModel.userRegistration(user)
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