package com.example.applicationelderpathtime.adapter

import android.annotation.SuppressLint
import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.CardFindWorkBinding
import com.example.applicationelderpathtime.model.RequestWorkModel
import com.example.applicationelderpathtime.model.ResponseWorkModel

class FindWorkAdapter(var dataList:List<ResponseWorkModel>):RecyclerView.Adapter<FindWorkAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: CardFindWorkBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardFindWorkBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(dataList[position]) {
                binding.tvCardName.setText(this.firstName + " "+this.lastName)
                binding.tvCardPhone.setText(this.phoneElder)
                binding.tvOrderNumber.setText("No."+Math.random()*100)
                binding.tvCardStartLocation.setText(this.houseCode+" "+this.district+" "+this.subDistrict+" "+this.province+" "+this.postCode)
                holder.itemView.setOnClickListener {
                    val bundle = bundleOf(
                        "id" to this.id.toString()
                    )
                    holder.itemView.findNavController().navigate(R.id.workDetailFragment,bundle)
                }
            }
        }
    }

    override fun getItemCount(): Int {
      return dataList.size
    }


}