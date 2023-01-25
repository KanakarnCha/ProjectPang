package com.example.applicationelderpathtime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentLoginBinding
import com.example.applicationelderpathtime.model.LoginModel
import com.example.applicationelderpathtime.network.RetrofitService
import com.example.applicationelderpathtime.repository.MainRepository
import com.example.applicationelderpathtime.session.SharePref
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var mainViewModel: MainViewModel
    var retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkState(view)
        binding.btnSubmitLogin.setOnClickListener {
            val loginModel = LoginModel(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
            mainViewModel = ViewModelProvider(this,
                MainViewModelFactory()
            ).get(MainViewModel::class.java)
            mainViewModel.loginUser(loginModel)
            mainViewModel.resLoginSuccess.observe(viewLifecycleOwner){
                val bundle = bundleOf("role" to it.roleUser)
                SharePref(view.context).saveDataUser(it.userId,it.roleUser)
                saveLogin(view)
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment,bundle)
                Toast.makeText(view?.context, "สำเร็จ", Toast.LENGTH_SHORT).show()
            }
            mainViewModel.resError.observe(viewLifecycleOwner){
                findNavController().popBackStack()
                findNavController().navigate(R.id.loginFragment)
                Toast.makeText(view?.context, "ไม่สำเร็จ", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRegister.setOnClickListener { // findNavController().navigate(R.id.summaryFragment)
            findNavController().navigate(R.id.menuRegisterFragment)
        }

    }
    fun saveLogin(view: View){
        val sharePref = SharePref(view.context)
        sharePref.stateLogin("1")
    }

    fun checkState(view: View){
        val sharePref = SharePref(view.context)
       val state =  sharePref.getLoginState()
        if (state == "1"){
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
            println("sss2"+state)
        }else{
            println("sss3"+state)
        }
    }
}