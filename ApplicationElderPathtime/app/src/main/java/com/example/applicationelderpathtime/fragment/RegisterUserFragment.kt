package com.example.applicationelderpathtime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentRegisterUserBinding
import com.example.applicationelderpathtime.model.RegisterModel
import com.example.applicationelderpathtime.network.RetrofitService
import com.example.applicationelderpathtime.repository.MainRepository
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory

class RegisterUserFragment : Fragment() {
    lateinit var binding: FragmentRegisterUserBinding
    val retrofitService = RetrofitService.getInstance()
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterUserBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegisterSubmit.setOnClickListener {
            mainViewModel = ViewModelProvider(this,MainViewModelFactory()).get(MainViewModel::class.java)

            val registerModel = RegisterModel(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString(),
                binding.edtIdCard.text.toString(),
                binding.edtFirstName.text.toString(),
                binding.edtLastName.text.toString(),
                "1",
                binding.edtPhone.text.toString()
            )
            mainViewModel.registerUser(registerModel)
            mainViewModel.resRegisterSuccess.observe(viewLifecycleOwner){
                if (it != null){
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.loginFragment)
                    Toast.makeText(view?.context, "สำเร็จ", Toast.LENGTH_SHORT).show()
                }
            }
            mainViewModel.resError.observe(viewLifecycleOwner){
                findNavController().popBackStack()
                findNavController().navigate(R.id.registerUserFragment)
                Toast.makeText(view?.context, "ไม่สำเร็จ", Toast.LENGTH_SHORT).show()
            }
        }
    }

}