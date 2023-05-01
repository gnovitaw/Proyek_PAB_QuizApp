package com.example.quizproject.ui.easy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.quizproject.R
import com.example.quizproject.databinding.FragmentEasylostBinding
/**
 * A simple [Fragment] subclass.
 */
class EasyLost : Fragment() {

    lateinit var binding: FragmentEasylostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_easylost,container,false)
        binding.btnTryAgain.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_quizeasyLostFragment_to_titleeasyFragment)
        }
        return binding.root
    }
}
