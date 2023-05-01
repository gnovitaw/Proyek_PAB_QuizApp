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
import com.example.quizproject.databinding.FragmentHardlostBinding

/**
 * A simple [Fragment] subclass.
 */
class HardLost : Fragment() {

    lateinit var binding: FragmentHardlostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_hardlost,container,false)
        binding.btnTryAgain.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_quizhardLostFragment_to_titlehardFragment)
        }
        return binding.root
    }
}
