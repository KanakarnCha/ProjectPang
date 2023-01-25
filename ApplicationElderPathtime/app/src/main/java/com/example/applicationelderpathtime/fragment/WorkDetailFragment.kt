package com.example.applicationelderpathtime.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentDataDetailBinding
import com.example.applicationelderpathtime.model.ReqUserElderStatusChange
import com.example.applicationelderpathtime.session.SharePref
import com.example.applicationelderpathtime.viewmodel.UserViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory


class WorkDetailFragment : Fragment() {
    lateinit var binding:FragmentDataDetailBinding;
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this,MainViewModelFactory()).get(UserViewModel::class.java)
        val bundle = arguments?.getString("id")
        userViewModel.findIdWork(bundle.toString())
        userViewModel.responseWorkDetailModelSuccess.observe(viewLifecycleOwner){
            data->
            binding.tvOrderNumber.setText(data.id.toString())
            binding.tvCardName.setText(data.firstName + " "+ data.lastName)
            binding.tvCardPhone.setText(data.phoneElder)
            binding.tvCardStartLocation.setText(data.houseCode+" "+data.district+" "+data.subDistrict+" "+data.province+" "+data.postCode)
            binding.tvCardDescription.setText(data.symptom.replace(", ","\n").replace("[", "").replace("]", "")+"\n"+"ราคาเริ่มต้น ${data.salary}")
            binding.btnMatch.setOnClickListener {
                val reqUserElderStatusChange = ReqUserElderStatusChange(
                    userId = SharePref(view.context).getUserId().toString(),
                    elderId = data.elderId,
                    statusWork = "1"
                )
                userViewModel.changStatusMatch(reqUserElderStatusChange)
                userViewModel.responseWorkDetailModelSuccess.observe(viewLifecycleOwner){
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.homeFragment)
                    Toast.makeText(view.context, "งานที่${it.toString()}", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }
}