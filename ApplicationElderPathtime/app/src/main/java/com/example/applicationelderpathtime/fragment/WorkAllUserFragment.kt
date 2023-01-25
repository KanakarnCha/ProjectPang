package com.example.applicationelderpathtime.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.adapter.FindWorkAdapter
import com.example.applicationelderpathtime.databinding.FragmentWorkAllUserBinding
import com.example.applicationelderpathtime.network.RetrofitService
import com.example.applicationelderpathtime.viewmodel.UserViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory


class WorkAllUserFragment : Fragment() {
    lateinit var binding:FragmentWorkAllUserBinding

    lateinit var recyclerViewAdapter:FindWorkAdapter
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkAllUserBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context)
        binding.recycleUi.layoutManager = layoutManager
        loadData()
    }

    private fun loadData() {
        userViewModel = ViewModelProvider(this,MainViewModelFactory()).get(UserViewModel::class.java)
        userViewModel.findAllWorkSt0()
        userViewModel.responseWorkModelSuccess.observe(viewLifecycleOwner){
            recyclerViewAdapter = FindWorkAdapter(it)
            binding.recycleUi.adapter = FindWorkAdapter(it)
            Log.e("test",it.toString())
        }
    }

}