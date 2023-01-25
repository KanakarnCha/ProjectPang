package com.example.applicationelderpathtime.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.applicationelderpathtime.MainActivity
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentProfileBinding
import com.example.applicationelderpathtime.session.SharePref
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory
import java.io.File
import kotlin.math.log


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChangeImage.setOnClickListener {
            findNavController().navigate(R.id.uploadImageProfileFragment)
        }
        setData(view)
        binding.btnLogout.setOnClickListener {
            val sharePref = SharePref(view.context)
            sharePref.stateLoginRemove()
            val intent = Intent(view.context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    fun setData(view: View){
        val mainViewModel = ViewModelProvider(this,MainViewModelFactory()).get(MainViewModel::class.java)
        mainViewModel.findUser(SharePref(view.context).getUserId().toString())
        mainViewModel.resLoginSuccess.observe(viewLifecycleOwner){

            if (it.imageUser != null){
                Glide.with(this).load("http://192.168.1.41:8080/image/${it.imageUser}") .diskCacheStrategy(
                    DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into( binding.clickImage)
            }

        }
    }

}