package com.example.quizproject.ui.medium

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.quizproject.R
import com.example.quizproject.databinding.FragmentMediumBinding
import com.example.quizproject.ui.medium.MediumFragmentDirections
import com.example.quizproject.ui.medium.QuestionMedium
import kotlinx.android.synthetic.main.fragment_medium.*

class MediumFragment : Fragment(){
    lateinit var binding: FragmentMediumBinding
    lateinit var currentQuestion: QuestionMedium
    private var questionIndex = 0
    private val maxNumberOfQuestion = 3
    lateinit var sharedPreferences: SharedPreferences
    lateinit var answers:ArrayList<String>
    lateinit var selectedAnswer:String
    var wrongAnswerList:ArrayList<String> = ArrayList()
    var score:Int = 0

    var questions:ArrayList<QuestionMedium> = arrayListOf(
        QuestionMedium("Berapa bulan memiliki 31 hari, lainnya 30 hari. Berapa bulan yang memiliki 28 hari ??",
            arrayListOf("12","1","9","10") ),
        QuestionMedium("Sebuah kereta api listrik bergerak ke utara dengan kecepatan 100 mph. Ke arah mana asapnya berhembus??",
            arrayListOf("Tidak ketiganya, Utara, Barat, Selatan") ),
        QuestionMedium("Ada 5 orang anak di dalam rumah. Ani sedang membaca buku Marta sedang memasak, Katy sedang bermain catur dan Maria sedang mencuci baju Sedang apakah anak ke lima??",
            arrayListOf("Bermain catur","Menonton TV","Bermain HP","Menyapu kamar") ),
        QuestionMedium("Ada sebuah bathtub berisi penuh dengan air di depan kamu. Disampingnya ada sendok, cangkir, dan emaber. Cara tercepat untuk mengosongkan bathtub tersebut adalah?",
            arrayListOf("Lainnya","Sendok","Cangkir","Ember") )
    )



    private fun setQuestion(){
        currentQuestion = questions[questionIndex]
        answers = ArrayList(currentQuestion.answerGroup)
        answers.shuffle()

        Log.d("ANSWERGROUP", answers[0]+ " "+answers[1]+ " "+answers[2]+ " "+answers[3]+ " ")
        Log.d("REALANSWER", currentQuestion.answerGroup[0])

        binding.invalidateAll()

    }

    private fun randomQuestion(){
        questions.shuffle()
        setQuestion()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_medium,container,false)

        randomQuestion()
        binding.quizmedium=this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        option1.setOnClickListener{
            checkAnswer(option1.text.toString())
        }
        option2.setOnClickListener{
            checkAnswer(option2.text.toString())
        }
        option3.setOnClickListener{
            checkAnswer(option3.text.toString())
        }
        option4.setOnClickListener{
            checkAnswer(option4.text.toString())
        }
        sharedPreferences = activity!!. getSharedPreferences("SP_HIGHEST_SCORE_MEDIUM",
            Context.MODE_PRIVATE
        )
    }

    private fun checkAnswer(answer:String){
        if(answer.equals(currentQuestion.answerGroup[0])){
            score+=1
        }
        else{
            wrongAnswerList.add(currentQuestion.theQuestion)
        }
        questionIndex++
        if(questionIndex<=maxNumberOfQuestion){
            setQuestion()
            binding.invalidateAll()
        }
        else{
            getScore()
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun getScore(){
        var highestScoreMedium = sharedPreferences.getInt("HIGHESTSCOREMEDIUM",0)
        if (score>highestScoreMedium){
            val editor = sharedPreferences.edit()
            editor.putInt("HIGHESTSCOREMEDIUM",score)
            editor.apply()

        }
        if(score>=2){
            val action = MediumFragmentDirections.actionQuizmediumFragmentToQuizmediumWonFragment(score,wrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)
        }
        else{
            val action = MediumFragmentDirections.actionQuizmediumFragmentToQuizmediumLostFragment(score,wrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)
        }
    }
}