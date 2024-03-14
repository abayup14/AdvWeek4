package com.example.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4.viewmodel.DetailViewModel
import com.example.advweek4.R
import com.example.advweek4.databinding.FragmentStudentDetailBinding

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel
    private lateinit var binding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            with(binding) {
                txtID.setText(it.id)
                txtName.setText(it.name)
                txtBOD.setText(it.dob)
                txtPhone.setText(it.phone)
            }
        })
    }

}