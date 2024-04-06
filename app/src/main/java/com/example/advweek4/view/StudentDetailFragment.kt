package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4.viewmodel.DetailViewModel
import com.example.advweek4.R
import com.example.advweek4.databinding.FragmentStudentDetailBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

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
        if (arguments != null) {
            val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel.fetch(id)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it
            with(binding) {
                txtID.setText(it.id)
                txtName.setText(it.name)
                txtBOD.setText(it.dob)
                txtPhone.setText(it.phone)

                val picasso = Picasso.Builder(requireView().context)
                picasso.listener { picasso, uri, exception ->
                    exception.printStackTrace()
                }
                picasso.build().load(it.photoUrl)
                    .into(imageView2, object : Callback {
                        override fun onSuccess() {
                            imageView2.visibility = View.VISIBLE
                        }

                        override fun onError(e: Exception?) {
                            Log.d("picasso error", e.toString())
                        }
                    })
            }

            binding.btnUpdate.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("messages", "five seconds")
                        MainActivity.showNotification(
                            student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_add_24
                        )
                    }
            }
        })
    }

}