package com.example.quizproject.ui.medium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.quizproject.R
import com.example.quizproject.databinding.FragmentMediumtitleBinding

/**
 * A simple [Fragment] subclass.
 */
class MediumTitleFragment : Fragment() {

    lateinit var binding : FragmentMediumtitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mediumtitle,container,false)
        binding.btnPlay.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_titlemediumFragment_to_quizmediumFragment)
        }
        return binding.root
    }


}