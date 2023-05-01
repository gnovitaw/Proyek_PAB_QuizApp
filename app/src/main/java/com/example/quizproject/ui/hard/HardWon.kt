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
import com.example.quizproject.databinding.FragmentHardwonBinding

/**
 * A simple [Fragment] subclass.
 */
class HardWon : Fragment() {
    lateinit var binding: FragmentHardwonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hardwon,container,false)
        binding.btnPlayNext.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_quizhardWonFragment_to_titlehardFragment)
        }
        return binding.root
    }


}
