package com.example.quizproject.ui.hard

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
import com.example.quizproject.databinding.FragmentHardBinding
import com.example.quizproject.ui.medium.MediumFragmentDirections
import kotlinx.android.synthetic.main.fragment_hard.*

class HardFragment : Fragment(){
    lateinit var binding: FragmentHardBinding
    lateinit var currentQuestion: QuestionHard
    private var questionIndex = 0
    private val maxNumberOfQuestion = 3
    lateinit var sharedPreferences: SharedPreferences
    lateinit var answers:ArrayList<String>
    lateinit var selectedAnswer:String
    var wrongAnswerList:ArrayList<String> = ArrayList()
    var score:Int = 0

    var questions:ArrayList<QuestionHard> = arrayListOf(
        QuestionHard("Ada 3 pintu di depanmu. Dibalik pintu 1, ada kobaran api yang siap menghadangmu.\n" +
                "Dibalik pintu 2, ada seorang pria dengan pistol di tangan. Di balik pintu 3, ada singa kelaparan karena gak makan selama 3 tahun.\n" +
                "Pintu mana yang kamu pilih ?",
            arrayListOf("Tiga","Satu","Dua","Lainnya") ),
        QuestionHard("Berapa hasil dari 30 dibagi 1/2 ditambah 10 ??",
            arrayListOf("70","25","35","20") ),
        QuestionHard("jika ada 12 ikan dan setengah dari mereka tenggelam, berapa banyak yang tersisa ?",
            arrayListOf("12","4","10","6") ),
        QuestionHard("saya mempunyai 3 apel. jika kamu mengambil 2 buah dari saya. Berapa apel yang kamu punya ??",
            arrayListOf("2","1","4","3") )
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_hard,container,false)

        randomQuestion()
        binding.quizhard=this
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
        sharedPreferences = activity!!. getSharedPreferences("SP_HIGHEST_SCORE_HARD",
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
        var highestScoreHard = sharedPreferences.getInt("HIGHESTSCOREHARD",0)
        if (score>highestScoreHard){
            val editor = sharedPreferences.edit()
            editor.putInt("HIGHESTSCOREHARD",score)
            editor.apply()

        }
        if(score>=2){
            val action = HardFragmentDirections.actionQuizhardFragmentToQuizhardWonFragment(score,wrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)
        }
        else{
            val action = HardFragmentDirections.actionQuizhardFragmentToQuizhardLostFragment(score,wrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)
        }
    }
}