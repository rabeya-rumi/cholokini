package com.rabeyarumi.cholokini

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.rabeyarumi.cholokini.base.BaseFragment
import com.rabeyarumi.cholokini.core.areAllPermissionsGranted
import com.rabeyarumi.cholokini.core.extract
import com.rabeyarumi.cholokini.core.requestPermissions
import com.rabeyarumi.cholokini.data.Product
import com.rabeyarumi.cholokini.databinding.FragmentUploadProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadProductFragment :
    BaseFragment<FragmentUploadProductBinding>(FragmentUploadProductBinding::inflate) {
        private lateinit var product: Product
    override fun setListener() {

        permissionsRequest = getPermissionsRequest()


        binding.apply {

            picProduct.setOnClickListener{
                requestPermissions(permissionsRequest, permissionList)

            }
         btnUploadProduct.setOnClickListener {
                loading.show()
                val name = etProductName.extract()
                val description = etProductDescrip.extract()
                val amount = etProductAmount.extract()
                val price = etProductPrice.extract()

             product = Product(
                 name = name,
                 description = description,
                 amount = amount.toInt(),
                 price = price.toDouble()
             )
             
             uploadProduct(product)
            }

        }
    }

    private fun getPermissionsRequest(): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

            if (areAllPermissionsGranted(permissionList)){
                Toast.makeText(requireContext(), "YES", Toast.LENGTH_SHORT).show()


                ImagePicker.with(this)
                    .compress(1024)
                    .maxResultSize(512, 512)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }

            }else{
                Toast.makeText(requireContext(), "No", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadProduct(product: Product) {

    }

    override fun allObserver() {
    }

    companion object{
        private val permissionList = arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
        )
    }

    private lateinit var permissionsRequest: ActivityResultLauncher<Array<String>>


    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                Log.d("TAG", "$fileUri")
                binding.picProduct.setImageURI(fileUri)

            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

}