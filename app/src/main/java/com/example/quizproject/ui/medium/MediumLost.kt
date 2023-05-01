package com.example.quizproject.ui.medium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.quizproject.R
import com.example.quizproject.databinding.FragmentMediumlostBinding

/**
 * A simple [Fragment] subclass.
 */
class MediumLost : Fragment() {

    lateinit var binding: FragmentMediumlostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mediumlost,container,false)
        binding.btnTryAgain.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_quizmediumLostFragment_to_titlemediumFragment)
        }
        return binding.root
    }
}
