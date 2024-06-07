package com.example.advweek4.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.model.Student
import com.example.advweek4.databinding.StudentListItemBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(var binding: StudentListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this
//        with(holder.binding) {
//            txtID.text = studentList[position].id
//            txtName.text = studentList[position].name
//
//            btnDetail.setOnClickListener {
//                val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//
//            val picasso = Picasso.Builder(holder.itemView.context)
//            picasso.listener { picasso, uri, exception ->
//                exception.printStackTrace()
//            }
//            picasso.build().load(studentList[position].photoUrl)
//                .into(imgStudent, object : Callback {
//                    override fun onSuccess() {
//                        progressImage.visibility = View.INVISIBLE
//                        imgStudent.visibility = View.VISIBLE
//                    }
//
//                    override fun onError(e: Exception?) {
//                        Log.e("picasso_error", e.toString())
//                    }
//                })
//        }
    }


    fun updateStudentList(newStudentList:ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}