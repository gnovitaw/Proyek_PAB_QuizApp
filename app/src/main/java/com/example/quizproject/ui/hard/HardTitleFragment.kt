package com.example.quizproject.ui.hard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.quizproject.R
import com.example.quizproject.databinding.FragmentHardtitleBinding

/**
 * A simple [Fragment] subclass.
 */
class HardTitleFragment : Fragment() {

    lateinit var binding : FragmentHardtitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hardtitle,container,false)
        binding.btnPlay.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_titlehardFragment_to_quizhardFragment)
        }
        return binding.root
    }


}