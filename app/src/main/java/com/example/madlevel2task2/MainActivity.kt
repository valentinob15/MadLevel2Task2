package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.question_layout.*

class MainActivity : AppCompatActivity() {
    private var questions = arrayListOf<Question>()
    private var questionsAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    /**
     * this method makes the layout for the rvQuestions recyclerView
     */
    private fun init() {
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionsAdapter
        addQuestions()

        createSwipe().attachToRecyclerView(rvQuestions)

    }


    private fun createSwipe(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT + ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            /**
             * this method puts removes the right questions out the list and the questions that are
             * wrong will be set at the end of the list
             */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT){
                    Snackbar.make(rvQuestions, "Item will not be deleted from list ", Snackbar.LENGTH_LONG).show()
                    val position = viewHolder.adapterPosition
                    val question = questions[position]
                    questions.removeAt(position)
                    questions.add(question)
                    questionsAdapter.notifyDataSetChanged()
                }else{
                    val position = viewHolder.adapterPosition
                    questions.removeAt(position)
                    questionsAdapter.notifyDataSetChanged()
                }


            }
        }
        return ItemTouchHelper(callback)

    }

    fun addQuestions() {
        for (i in Question.QUIZ_QUESTIONS.indices) {
            questions.add(Question(Question.QUIZ_QUESTIONS[i]))
            questionsAdapter.notifyDataSetChanged()
        }
    }
}




