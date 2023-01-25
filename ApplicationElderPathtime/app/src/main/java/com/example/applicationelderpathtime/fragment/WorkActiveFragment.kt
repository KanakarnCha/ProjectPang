package com.example.applicationelderpathtime.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentWorkActiveBinding
import com.example.applicationelderpathtime.model.LoginModel
import com.example.applicationelderpathtime.model.ReqUserAndStatus
import com.example.applicationelderpathtime.model.ResponseWorkModel
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodel.UserViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory
import com.google.gson.Gson


class WorkActiveFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: FragmentWorkActiveBinding
    lateinit var data : ResponseWorkModel
    var page: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentWorkActiveBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissions()
        val getData = arguments?.getString("dataWork")
        data = Gson().fromJson(getData, ResponseWorkModel::class.java)
        page = arguments?.getInt("page")
        setData()
        Log.e("TEST",data.toString())
    }
    @SuppressLint("SetTextI18n")
    fun setData(){
        if (page == 1){
            mainViewModel =ViewModelProvider(this,MainViewModelFactory()).get(MainViewModel::class.java)
            if (data.userId != "" ){
                mainViewModel.findUser(data.userId.toString())
                binding.linear4.visibility = View.GONE
                binding.linear5.visibility = View.VISIBLE
                mainViewModel.resLoginSuccess.observe(viewLifecycleOwner){
                        it ->
                    binding.tvOrderNumber.setText(data.id.toString())
                    binding.tvCardName.setText(it.firstnameUser + " " + it.lastNameUser)
                    binding.tvCardPhone.setText(it.phoneNumber)
                    Glide.with(this).load("http://192.168.1.41:8080/image/${it.imageUser}") .diskCacheStrategy(
                        DiskCacheStrategy.NONE)
                        .skipMemoryCache(true).into( binding.imageUser)
                    binding.btnCallPhone.setOnClickListener {
                            e->
                        callPhone(it.phoneNumber)

                    }
                }
            }else{
                binding.relaDataDetail.visibility = View.GONE
                binding.textNullActive.visibility = View.VISIBLE
            }

        }else{
            binding.tvCardName.setText(data.firstName + " " + data.lastName)
            binding.tvCardPhone.setText(data.phoneElder)
            binding.linear4.visibility =View.VISIBLE
            binding.tvCardStartLocation.setText(data.houseCode+" "+data.district+" "+data.subDistrict+" "+data.province+" "+data.postCode)
            binding.btnCallPhone.setOnClickListener {
                    e->
                callPhone(data.phoneElder)

            }
        }
        binding.btnSuccessWork.setOnClickListener {
            val userViewModel = ViewModelProvider(this,MainViewModelFactory()).get(UserViewModel::class.java)
            userViewModel.workSuccess(data.id.toString(),"2")
            userViewModel.responseWorkDetailModelSuccess.observe(viewLifecycleOwner){
                findNavController().popBackStack()
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }
        }


    }
    private fun checkPermissions(){
        if (ActivityCompat.checkSelfPermission(binding.root.context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.CALL_PHONE),101) }
        }

    }
    fun callPhone(phone:String){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.setData(Uri.parse("tel:" +phone));
        startActivity(callIntent)
    }

}