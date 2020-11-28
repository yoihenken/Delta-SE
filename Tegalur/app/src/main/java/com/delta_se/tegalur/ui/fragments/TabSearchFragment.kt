package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.model.DataPariwisata
import com.delta_se.tegalur.data.model.DataRecycler
import com.delta_se.tegalur.databinding.FragmentTabSearchBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
import com.delta_se.tegalur.ui.adapter.ListCategoryAdapter

class TabSearchFragment (val modeCategory : String) : Fragment() {

    private lateinit var binding: FragmentTabSearchBinding

    private var listBerita = ArrayList<DataBerita>()
    private var listPariwisata = ArrayList<DataPariwisata>()

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

        when(modeCategory){
            "BERITA" -> {
                getDataBerita()
                dataRecycler.clear()
                for (item in listBerita){
                    dataRecycler.add(
                        DataRecycler(
                            item.title,
                            item.image,
                            item.date,
                            item.isSaved
                        ))
                }
                binding.rvSearch.apply {
                    layoutManager = LinearLayoutManager(activity)
                    setHasFixedSize(true)
//                    adapter = ListBeritaAdapter(listBerita)
                    adapter = ListCategoryAdapter(dataRecycler)

                }
            }
            "PARIWISATA" -> {
                getDataPariwisata()
                dataRecycler.clear()
                for (item in listPariwisata){
                    dataRecycler.add(
                        DataRecycler(
                            item.title,
                            item.image,
                            item.address,
                            item.isSaved
                    ))
                }
                binding.rvSearch.apply {
                    layoutManager = LinearLayoutManager(activity)
                    setHasFixedSize(true)
                    adapter = ListCategoryAdapter(dataRecycler)
                }
            }
        }
    }

    private fun getDataBerita() : ArrayList<DataBerita> {
        val dataBerita : ArrayList<DataBerita> = DataDummy().getDataBerita()
        listBerita.addAll(dataBerita)
        return listBerita
    }

    private fun getDataEvent(){}

    private fun getDataPariwisata() : ArrayList<DataPariwisata> {
        val dataPariwisata : ArrayList<DataPariwisata> = DataDummy().getPariwisata()
        listPariwisata.addAll(dataPariwisata)
        return listPariwisata
    }

    private fun getDataKuliner(){}

    private fun getDataOleh(){}

    private fun getDataPenginapan(){}


    companion object {}
}