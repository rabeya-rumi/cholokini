package com.rabeyarumi.cholokini.db.starter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.rabeyarumi.cholokini.R
import com.rabeyarumi.cholokini.base.BaseFragment
import com.rabeyarumi.cholokini.databinding.FragmentStartBinding
import com.rabeyarumi.cholokini.db.dashboard.seller.SellerDashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {



      override fun setListener() {

          setupAutoLogin()

        with(binding){
            btnLogin.setOnClickListener{

                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }

            btnRegister.setOnClickListener {

                findNavController().navigate(R.id.action_startFragment_to_registerFragment)
            }
        }
    }


    private fun setupAutoLogin(){
        FirebaseAuth.getInstance().currentUser?.let {
        startActivity(Intent(requireContext(), SellerDashboardActivity::class.java))
            requireActivity().finish()

//            findNavController().navigate(R.id.action_startFragment_to_dashboardFragment)
        }
    }

    override fun allObserver() {

    }


}