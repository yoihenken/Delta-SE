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
import com.delta_se.tegalur.utils.Helpers.toDataPariwisata
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem

class TabSearchFragment () : Fragment() {

    private lateinit var binding: FragmentTabSearchBinding

    private var listBerita = ArrayList<DataBerita>()
    private var listPariwisata = ArrayList<DataPariwisata>()
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

    private var dataRecycler = ArrayList<DataRecycler>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabSearchBinding.bind(view)

        var index = FragmentPagerItem.getPosition(arguments)
        showFragment(index)
    }

    fun showFragment(indeks : Int){
        when(indeks){
            0 -> {
                val modeAdapter = "PARIWISATA"

                model.getPariwisata()
                model.pariwisata.observe(viewLifecycleOwner) {
                    if (it != null) populateData(it.toDataPariwisata(), modeAdapter)
                }
            }
            1 -> {}
            2 -> {
//                getDataPariwisata()
//                dataRecycler.clear()
//                for (item in listPariwisata) {
//                    dataRecycler.add(
//                            DataRecycler(
//                                    item.id,
//                                    item.title!!,
//                                    item.image!!,
//                                    item.address!!,
//                                    item.isSaved
//                            )
//                    )
//                }
//                binding.rvSearch.apply {
//                    layoutManager = LinearLayoutManager(activity)
//                    setHasFixedSize(true)
//                    adapter = ListCategoryAdapter(dataRecycler, requireActivity(), "PARIWISATA")
//                }
            }
            3 -> {}
        }
    }

    private fun populateData(it: List<Any>, modeAdapter : String) = with(binding) {
        rvSearch.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = ListCategoryAdapter(it, requireActivity(), modeAdapter)
        }
    }
}