package com.example.applicationelderpathtime.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentSummaryBinding
import com.example.applicationelderpathtime.network.RetrofitService
import com.example.applicationelderpathtime.repository.MainRepository
import com.example.applicationelderpathtime.session.SharePrefWork
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodelFactory.MainViewModelFactory


class SummaryFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel
    var retrofitService = RetrofitService.getInstance()
    lateinit var binding: FragmentSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(view)
        binding.btnNextSubmit.setOnClickListener {
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle("เรียกใช้งาน")
            builder.setMessage("คุณต้องการยืนยัน ใช่ หรือ ไม่")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                val sharePrefWork = SharePrefWork(view.context)
                val workData = sharePrefWork.getData()
                mainViewModel = ViewModelProvider(this,MainViewModelFactory()).get(MainViewModel::class.java)
                mainViewModel.addWork(workData)
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }
            builder.show()
        }

    }
    @SuppressLint("SetTextI18n")
    fun setData(view: View){
        val sharePrefWork = SharePrefWork(view.context)
        val workData = sharePrefWork.getData()
        val string = "[ผู้ป่วยติดเตียง, ดูดเสมหะ]"

        val caText = workData.symptom.replace(", ","\n").replace("[", "").replace("]", "")

        binding.tvName.setText(workData.firstName+ " "+workData.lastName)
        binding.tvSex.setText(workData.sex)
        binding.tvAge.setText(workData.age+" ปี")
        binding.tvWeight.setText(workData.weight+" กก.")
        binding.tvHight.setText(workData.height + " ซม.")
        binding.tvAddress.setText(workData.houseCode+" "+workData.district+" "+workData.subDistrict+" "+workData.province+" "+workData.postCode)
        binding.tvSy.setText(caText.toString())
        binding.tvStartDate.setText(workData.startDate)
        binding.tvEndDate.setText(workData.endDate)
        binding.tvSalary.setText(workData.salary)
        binding.tvPhone.setText(workData.phoneElder)
    }
}