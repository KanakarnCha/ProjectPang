package com.example.applicationelderpathtime.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.Data
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentCreateWorkBinding
import com.example.applicationelderpathtime.model.RequestWorkModel
import com.example.applicationelderpathtime.session.SharePref
import com.example.applicationelderpathtime.session.SharePrefWork


class CreateWorkFragment : Fragment() {
    lateinit var binding: FragmentCreateWorkBinding
    private var checkBoxsymptom: String? = null
    val selectedOptions: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateWorkBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(view.context)
        val arraySex = arrayListOf("ชาย", "หญิง")
        val arrayProvince = Data().arrayProvince
        val arrayDistrict = Data().district
        val adapterProvince = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, arrayProvince)
        val adapter =
            ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, arraySex)
        val adapterDis =
            ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, arrayDistrict)
        binding.edtSex.setAdapter(adapter)
        binding.edtProvince.setAdapter(adapterProvince)
        binding.edtDistrict.setAdapter(adapterDis)

    }


    @SuppressLint("SuspiciousIndentation")
    fun setData(context: Context) {

            // check if checkbox1 is checked\
        binding.rd1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd1.text.toString())
            }else{
                checkItem(binding.rd1.text.toString())
            }
        }
        binding.rd2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd2.text.toString())
            }else{
                checkItem(binding.rd2.text.toString())
            }
        }
        binding.rd3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd3.text.toString())
            }else{
                checkItem(binding.rd3.text.toString())
            }
        }
        binding.rd4.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd4.text.toString())
            }else{
                checkItem(binding.rd4.text.toString())
            }
        }
        binding.rd5.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd5.text.toString())
            }else{
                checkItem(binding.rd5.text.toString())
            }
        }
        binding.rd6.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd6.text.toString())
            }else{
                checkItem(binding.rd6.text.toString())
            }
        }
        binding.rd7.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd7.text.toString())
            }else{
                checkItem(binding.rd7.text.toString())
            }
        }
        binding.rd8.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd8.text.toString())
            }else{
                checkItem(binding.rd8.text.toString())
            }
        }
        binding.rd9.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd9.text.toString())
            }else{
                checkItem(binding.rd9.text.toString())
            }
        }
        binding.rd10.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                selectedOptions.add(binding.rd10.text.toString())
            }else{
                checkItem(binding.rd10.text.toString())
            }
        }

        binding.btnAddWorkNext.setOnClickListener {

            val requestWorkModel = RequestWorkModel(
                firstName = binding.edtFirstName.text.toString(),
                lastName = binding.edtLastName.text.toString(),
                houseCode = binding.edtHouseCode.text.toString(),
                road = binding.edtRoad.text.toString(),
                province = binding.edtProvince.text.toString(),
                district = binding.edtDistrict.text.toString(),
                subDistrict = binding.edtSubDistrict.text.toString(),
                postCode = binding.edtPostCode.text.toString(),
                sex = binding.edtSex.text.toString(),
                age = binding.edtAge.text.toString(),
                weight = binding.edtWeight.text.toString(),
                height = binding.edtHight.text.toString(),
                elderId = SharePref(context).getUserId().toString(),
                userId = "",
                imageSlip = "",
                salary = "",
                startDate = "",
                endDate = "",
                statusWork = "",
                symptom = selectedOptions.toString(),
                phoneElder = binding.edtPhoneNumber.text.toString()
            )
            SharePrefWork(context).saveWorkData(requestWorkModel)
            println(SharePrefWork(context).getData())
            findNavController().popBackStack()
            findNavController().navigate(R.id.workAddDateFragment)
        }


    }
    fun checkItem(item:String){
        val index = selectedOptions.indexOf(item)
        selectedOptions.removeAt(index)
    }
}