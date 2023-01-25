package com.example.applicationelderpathtime.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentHomeBinding
import com.example.applicationelderpathtime.model.ReqUserAndStatus
import com.example.applicationelderpathtime.session.SharePref
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodel.UserViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory
import com.google.gson.Gson


class HomeFragment : Fragment() {
    lateinit var userViewModel: UserViewModel
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: FragmentHomeBinding
    lateinit var role:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        role = SharePref(view.context).getRoleUser().toString()
        checkData(view)
        if (role == "1"){
            binding.consPage1.visibility = View.VISIBLE

        }else if (role == "2"){
            binding.consPage2.visibility = View.VISIBLE
        }
        binding.btnAddWord.setOnClickListener {
            findNavController().navigate(R.id.createWorkFragment)
        }



        //user
        binding.btnFindWork.setOnClickListener {
            findNavController().navigate(R.id.workAllUserFragment)
        }



        //Toast.makeText(view.context, "TEST1"+SharePref(view.context).getUserId()+"TEST2"+SharePref(view.context).getRoleUser(), Toast.LENGTH_SHORT).show()
    }
    fun checkData(view: View){
        userViewModel = ViewModelProvider(this,MainViewModelFactory()).get(UserViewModel::class.java)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory()).get(MainViewModel::class.java)



        if (role == "1"){
            val model = ReqUserAndStatus(
                id  = SharePref(view.context).getUserId().toString(),

                statusWork = "0"
            )
            mainViewModel.searchWorkElder(model)
            mainViewModel.responseWorkDetailModelSuccess.observe(viewLifecycleOwner){
                data ->
                if (data == null){
                    binding.btnWorkHistoryUser.visibility = View.GONE
                }else{
                    binding.btnAddWord.visibility = View.GONE
                    binding.btnWorkHistoryUser.setText("มีงานค้างอยู่กรุณาเข้าไปดู 1")
                    binding.btnWorkHistoryUser.setOnClickListener {
                        val bundle = bundleOf("dataWork" to Gson().toJson(data),"page" to 1)
                        findNavController().navigate(R.id.workActiveFragment,bundle)
                    }
                }
            }



        }else{
            val model = ReqUserAndStatus(
                id  = SharePref(view.context).getUserId().toString(),

                statusWork = "1"
            )
            userViewModel.findWorkUserIdAndStatus(model)
            userViewModel.responseWorkDetailModelSuccess.observe(viewLifecycleOwner){ data ->
                if (data == null){
                    binding.btnFindWork.setText("หางาน")
                    binding.btnHistoryPathTime.visibility = View.GONE
                }else{
                    binding.btnFindWork.visibility = View.GONE
                    binding.btnHistoryPathTime.setText("มีงานค้างอยู่กรุณาเข้าไปดู")
                    binding.btnHistoryPathTime.setOnClickListener {
                        val bundle = bundleOf("dataWork" to Gson().toJson(data),"page" to 2)
                        findNavController().navigate(R.id.workActiveFragment,bundle)
                    }
                }

            }
        }

    }
}