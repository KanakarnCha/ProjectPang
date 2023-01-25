package com.example.applicationelderpathtime.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentWorkAddDateBinding
import com.example.applicationelderpathtime.model.RequestWorkModel
import com.example.applicationelderpathtime.session.SharePrefWork
import org.joda.time.Days
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*


class WorkAddDateFragment : Fragment() {
    lateinit var binding: FragmentWorkAddDateBinding
    var checkDay = false
    var checkMonth = false
    var startDay = ""
    var startEnd = ""
    var salary = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkAddDateBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate(view)
        checkClick()

        binding.edtNextSubmit.setOnClickListener {
            println(salary+startDay + startEnd)
            saveData(view)
            findNavController().popBackStack()
            findNavController().navigate(R.id.summaryFragment)
        }
        binding.edtEndDate.doAfterTextChanged {
            try {
                calSalary()
            }catch (e:Exception){
                println("Fail")
            }
        }
    }

    fun checkClick(){
        binding.checkboxDay.setOnClickListener {
            if (checkDay){
                checkDay = false
                binding.linDay1.visibility = View.GONE
            }else{
                checkDay = true
                binding.linDay1.visibility = View.VISIBLE
            }

        }

    }
    fun setDate(view: View){
        binding.edtStartDate.setOnClickListener {
          onCalenda(binding.edtStartDate,view,1)
        }
        binding.edtEndDate.setOnClickListener {
            onCalenda(binding.edtEndDate,view,2)
        }

    }
    fun calSalary(){
        try {
            val currentDate = startDay
            val finalDate = startEnd
            val date1: Date
            val date2: Date
            val dates = SimpleDateFormat("MM/dd/yyyy")
            date1 = dates.parse(currentDate)
            date2 = dates.parse(finalDate)
            val difference: Long = abs(date1.time - date2.time)
            val differenceDates = difference / (24 * 60 * 60 * 1000)
            val dayDifference = differenceDates.toString()
            println("dayDifference" + dayDifference)
            salary = ((Integer.parseInt(dayDifference)+1)*700).toString()
            binding.tvSalary.setText(salary.toString())
        }catch (e:Exception){
            Log.e("ERROR",e.message.toString())
        }


    }
    fun onCalenda( edittext:EditText,view: View,check:Int){
        val c = Calendar.getInstance()

        // on below line we are getting
        // our day, month and year.
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // on below line we are creating a
        // variable for date picker dialog.
        val datePickerDialog = DatePickerDialog(
            view.context,
            { view, year, monthOfYear, dayOfMonth ->
                var daylast =""
                var monthlast =""
                if (monthOfYear + 1 <= 9){monthlast =  "0${monthOfYear + 1}"}else{monthlast = ((monthOfYear + 1).toString()) }
                if (dayOfMonth <= 9){daylast =  "0${dayOfMonth}"}else{daylast = dayOfMonth.toString() }
                edittext.setText(daylast + "/" + monthlast + "/" + year)
                if (check == 1){
                    startDay = (monthlast + "/" + daylast + "/" + year)
                }else{
                    startEnd = (monthlast + "/" + daylast + "/" + year)
                }

                calSalary()
            },
            year,
            month,
            day
        )
        // at last we are calling show
        // to display our date picker dialog.
        datePickerDialog.show()
    }

    fun saveData(view: View){

        val sharePrefWork = SharePrefWork(view.context)
        val requestWorkModel = sharePrefWork.getData()
        requestWorkModel.startDate = startDay
        requestWorkModel.endDate = startEnd
        requestWorkModel.salary = salary
        requestWorkModel.statusWork = "0"
        sharePrefWork.saveWorkData(requestWorkModel)
        println(sharePrefWork.getData())
    }
}