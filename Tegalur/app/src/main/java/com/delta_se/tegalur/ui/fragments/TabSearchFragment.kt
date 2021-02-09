package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.data.model.DataRecycler
import com.delta_se.tegalur.databinding.FragmentTabSearchBinding
import com.delta_se.tegalur.ui.adapter.ListCategoryAdapter
import com.delta_se.tegalur.ui.adapter.ListSimpanAdapter
import com.delta_se.tegalur.utils.Helpers.toDataKuliner
import com.delta_se.tegalur.utils.Helpers.toDataOleh
import com.delta_se.tegalur.utils.Helpers.toDataPariwisata
import com.delta_se.tegalur.utils.Helpers.toDataPenginapan
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem

class TabSearchFragment () : Fragment() {

    private lateinit var binding: FragmentTabSearchBinding
    private val model : TabSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabSearchBinding.bind(view)
        var index = FragmentPagerItem.getPosition(arguments)
        showFragment(index)
    }

    private fun convertPageIdToId(pageId: String) : Int{
        val clear = pageId.split(" ").toTypedArray()
        return clear[1].toInt()
    }

    fun showFragment(indeks : Int){
        when(indeks){
            0 -> {
                model.getSavedTourism(requireActivity().application, requireActivity())
                model.getPariwisata()
                val modeAdapter = "PARIWISATA"
//                var idPariw : Int
//                model.saved.observe(viewLifecycleOwner){
//                    it.forEach { dataSave ->
//                        if (dataSave.type.equals(modeAdapter)){
//                            model.getPariwisata()
//                        }
//
//                    }
//                }

                model.pariwisata.observe(viewLifecycleOwner) {
                    populateData(it.toDataPariwisata(), modeAdapter)
                }
            }
            1 -> {
                model.getSavedKul(requireActivity().application, requireActivity())
                model.getKuliner()
                val modeAdapter = "KULINER"
                model.kuliner.observe(viewLifecycleOwner){
                    populateData(it.toDataKuliner(), modeAdapter)
                }
            }
            2 -> {
                model.getSavedOleh(requireActivity().application, requireActivity())
                model.getOleh()
                val modeAdapter = "OLEH"
                model.oleh.observe(viewLifecycleOwner){
                    populateData(it.toDataOleh(), modeAdapter)
                }
            }
            3 -> {
                model.getSavedLodging(requireActivity().application, requireActivity())
                model.getPenginapan()
                val modeAdapter = "PENGINAPAN"

                model.penginapan.observe(viewLifecycleOwner){
                    populateData(it.toDataPenginapan(), modeAdapter)
                }
            }
        }
    }

    private fun populateData(it: List<Any>, modeAdapter : String) = with(binding) {
        rvSearch.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = ListCategoryAdapter(it, context, modeAdapter, model)
        }
    }
}