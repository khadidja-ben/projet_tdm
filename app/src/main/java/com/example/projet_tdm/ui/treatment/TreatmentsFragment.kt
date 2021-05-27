package com.example.projet_tdm.ui.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projet_tdm.R


class TreatmentsFragment : Fragment() {

    private lateinit var treatmentsViewModel: TreatmentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        treatmentsViewModel =
            ViewModelProvider(this).get(TreatmentsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_treatments, container, false)
        val textView: TextView = root.findViewById(R.id.text_treatments)
        treatmentsViewModel .text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}