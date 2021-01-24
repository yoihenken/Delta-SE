package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.FragmentSimpanBinding

class SimpanFragment : Fragment() {

    private lateinit var binding : FragmentSimpanBinding
    private lateinit var model : SimpanViewModel
    private var mainData = DataSave()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simpan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSimpanBinding.bind(view)



    }

    companion object {}
}