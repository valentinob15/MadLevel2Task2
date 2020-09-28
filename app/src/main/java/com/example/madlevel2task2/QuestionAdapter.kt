package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.QuestionLayoutBinding


class QuestionAdapter(var questions: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.question_layout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.databinding(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = QuestionLayoutBinding.bind(itemView)
        fun databinding(question: Question) {
            binding.question1.text = question.question
        }
    }


}