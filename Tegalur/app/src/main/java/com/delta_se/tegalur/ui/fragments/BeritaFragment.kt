package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.databinding.FragmentBeritaBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter

class BeritaFragment : Fragment() {

    private lateinit var binding: FragmentBeritaBinding
    private val list = ArrayList<DataBerita>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getDataBerita() : ArrayList<DataBerita>{
        val dataBerita : ArrayList<DataBerita> = DataDummy().getDataBerita()
        list.addAll(dataBerita)
        return list
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_berita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBeritaBinding.bind(view)

        getDataBerita()

        binding.rvBerita.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = ListBeritaAdapter(list, context)
        }
    }

    companion object {}
}