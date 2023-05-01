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
import com.example.quizproject.databinding.FragmentEasywonBinding

/**
 * A simple [Fragment] subclass.
 */
class EasyWon : Fragment() {
    lateinit var binding: FragmentEasywonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_easywon,container,false)
        binding.btnPlayNext.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_quizeasyWonFragment_to_titleeasyFragment)
        }
        return binding.root
    }


}
