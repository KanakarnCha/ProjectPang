package com.example.applicationelderpathtime.fragment

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.applicationelderpathtime.R
import com.example.applicationelderpathtime.databinding.FragmentMenuRegisterBinding


class MenuRegisterFragment : Fragment() {

    lateinit var binding: FragmentMenuRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuRegisterBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegisterUser.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.registerUserFragment)
        }
        binding.btnRegisterPathTime.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.registerPathTimeFragment)
        }
    }

}