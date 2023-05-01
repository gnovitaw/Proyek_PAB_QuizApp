package com.example.quizproject.ui.easy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.quizproject.R
import com.example.quizproject.databinding.FragmentEasytitleBinding

/**
 * A simple [Fragment] subclass.
 */
class EasyTitleFragment : Fragment() {

    lateinit var binding : FragmentEasytitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_easytitle,container,false)
        binding.btnPlay.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_titleeasyFragment_to_quizeasyFragment)
        }
        return binding.root
    }


}