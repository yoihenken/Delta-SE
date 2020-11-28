package com.delta_se.tegalur.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.R
import com.delta_se.tegalur.SearchActivity
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.databinding.FragmentBerandaBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter

class BerandaFragment() : Fragment() {

    private lateinit var binding: FragmentBerandaBinding
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
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBerandaBinding.bind(view)

        getDataBerita()

        binding.apply {
            categoryMenu.apply {
                categoryPariwisata.setOnClickListener {
                    val intentIndex = 1
                    val moveWithIntent = Intent(activity, SearchActivity::class.java)
                    moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                    startActivity(moveWithIntent)
                }
            }

            rvBeranda.apply {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = ListBeritaAdapter(list)
            }
        }
    }
    companion object {}
}