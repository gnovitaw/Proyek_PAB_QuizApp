package com.example.quizproject.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.quizproject.R
import com.example.quizproject.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(){
    val user = FirebaseAuth.getInstance().currentUser
    var highestScoreEasy:String = "0"
    var highestScoreMedium:String = "0"
    var highestScoreHard:String = "0"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false)
        binding.userprofile= this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user.let {
            Glide.with(this)
                .load(user?.photoUrl)
                .fitCenter()
                .placeholder(R.drawable.logo)
                .into(userimage)
        }
        sharedPreferences = activity!!.getSharedPreferences("SP_HIGHEST_SCORE_EASY", Context.MODE_PRIVATE)
        highestScoreEasy = sharedPreferences.getInt("HIGHESTSCOREEASY",0).toString()
        sharedPreferences = activity!!.getSharedPreferences("SP_HIGHEST_SCORE_MEDIUM", Context.MODE_PRIVATE)
        highestScoreMedium = sharedPreferences.getInt("HIGHESTSCOREMEDIUM",0).toString()
        sharedPreferences = activity!!.getSharedPreferences("SP_HIGHEST_SCORE_HARD", Context.MODE_PRIVATE)
        highestScoreHard = sharedPreferences.getInt("HIGHESTSCOREHARD",0).toString()

    }
}