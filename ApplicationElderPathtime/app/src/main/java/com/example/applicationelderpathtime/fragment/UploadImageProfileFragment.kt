package com.example.applicationelderpathtime.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentUploadImageProfileBinding
import com.example.applicationelderpathtime.fragment.UploadImageProfileFragment.Companion.pic_id
import com.example.applicationelderpathtime.session.SharePref
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream


class UploadImageProfileFragment : Fragment() {

    lateinit var photo:Bitmap
    companion object {
        // Define the pic id
        private const val pic_id = 123
    }
    lateinit var binding:FragmentUploadImageProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUploadImageProfileBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cameraButton.setOnClickListener {
            val camera_intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            } else {
                TODO("VERSION.SDK_INT < CUPCAKE")
            }
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, pic_id)
        }
    }
    // This method will help to retrieve the image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            photo = (data!!.extras!!["data"] as Bitmap?)!!
            // Set the image in imageview for display
            binding.clickImage.setImageBitmap(photo)
            binding.btUpload.visibility = View.VISIBLE
            binding.btUpload.setOnClickListener {
                val byteArrayOutputStream = ByteArrayOutputStream()
                val bitmapScaled = Bitmap.createScaledBitmap(photo, 720, 1080, true)
                bitmapScaled.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
                val imageBytes = byteArrayOutputStream.toByteArray()
                val file = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageBytes)
                val image = MultipartBody.Part.createFormData("file", "photo.jpg", file)
                uploadImage(view?.let { it1 -> SharePref(it1.context).getUserId() },image)
            }
        }
    }
    fun uploadImage(name: String?, photo: MultipartBody.Part){
        val mainViewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
        if (name != null) {
            mainViewModel.uploadImage(name,photo)
            findNavController().popBackStack()
            findNavController().navigate(R.id.profileFragment)
        }
    }
}