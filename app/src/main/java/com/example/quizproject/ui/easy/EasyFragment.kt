package com.example.quizproject.ui.easy

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
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
import com.example.quizproject.databinding.FragmentEasyBinding
import kotlinx.android.synthetic.main.fragment_easy.*


class EasyFragment : Fragment(){
    lateinit var binding: FragmentEasyBinding
    lateinit var currentQuestion: QuestionEasy
    private var questionIndex = 0
    private val maxNumberOfQuestion = 3
    var wrongAnswerList:ArrayList<String> = ArrayList()
    lateinit var sharedPreferences: SharedPreferences
    lateinit var answers:ArrayList<String>
    lateinit var selectedAnswer:String

    var score:Int = 0

    var questions:ArrayList<QuestionEasy> = arrayListOf(
        QuestionEasy("Petani memiliki 15 domba, semuanya mati kecuali 8 ekor. Berapa banyak yang tersisa ??",
            arrayListOf("8","7","6","5") ),
        QuestionEasy("Berapa kali kamu bisa mengambil 5 dari 25 ??",
            arrayListOf("1","12","5","3") ),
        QuestionEasy("Sebuah kereta api listrik bergerak ke utara dengan kecepatan 100 mph. Ke arah mana asapnya berhembus??",
            arrayListOf("1","50","25","30") ),
        QuestionEasy("Saat mengikuti sebuah balapan, kamu menyalip orang di posisi nomor 2. Ada di posisi berapa kamu sekarang ??",
            arrayListOf("2","1","4","3") )
    )


    private fun setQuestion(){
        currentQuestion = questions[questionIndex]
        answers = ArrayList(currentQuestion.answerGroup)
        answers.shuffle()

        Log.d("ANSWERGROUP", answers[0]+ " "+answers[1]+ " "+answers[2]+ " "+answers[3]+ " ")
        //jawaban benar nya ada di array 0
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_easy,container,false)

        randomQuestion()
        binding.quizeasy=this
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
        sharedPreferences = activity!!. getSharedPreferences("SP_HIGHEST_SCORE_EASY", MODE_PRIVATE)

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
        var highestScoreEasy = sharedPreferences.getInt("HIGHESTSCOREEASY",0)
        if (score>highestScoreEasy){
            val editor = sharedPreferences.edit()
            editor.putInt("HIGHESTSCOREEASY",score)
            editor.apply()
        }
        if(score>=2){

            val action = EasyFragmentDirections.actionQuizeasyFragmentToQuizeasyWonFragment(score,wrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)
        }
        else{
            val action = EasyFragmentDirections.actionQuizeasyFragmentToQuizeasyLostFragment(score,wrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)
        }
    }
}