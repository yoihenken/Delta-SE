package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.FragmentTabSimpankBinding
import com.delta_se.tegalur.ui.adapter.ListSimpanAdapter
import com.delta_se.tegalur.utils.Helpers.toDataBeritaFromRoom
import com.delta_se.tegalur.utils.Helpers.toDataEventFromRoom
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem

class TabSimpankFragment : Fragment() {

    private val model : TabSimpankViewModel by viewModels()
    private lateinit var binding : FragmentTabSimpankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_simpank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabSimpankBinding.bind(view)

        model.getSavedData(requireActivity().application, requireActivity())

        val index = FragmentPagerItem.getPosition(arguments)
        showFragment(index)
    }

    private fun convertPageIdToPage(pageId : String) : Int{
        val clear = pageId.split(" ").toTypedArray()
        return clear[0].toInt()
    }

    private fun convertPageIdToId(pageId: String) : Int{
        val clear = pageId.split(" ").toTypedArray()
        return clear[1].toInt()
    }

    private fun showFragment(index: Int) {
        when(index){
            0 -> {
                val modeAdapter = "BERITA"
                var pageBerita: Int? = null
                var idBerita: Int? = null

                model.saved.observe(viewLifecycleOwner, {
                    Log.d("TabSimpankFragment", "BERITA model.saved: ${it}")
                    if (it.isEmpty()) removePopulateData()
                    it.forEach { dataSave ->
                        Log.d("TabSimpankFragment", "dataSave: $dataSave")
                        if (dataSave.type.equals(modeAdapter)) {
                            pageBerita = convertPageIdToPage(dataSave.pageid!!)
                            idBerita = convertPageIdToId(dataSave.pageid!!)
                            Log.d(
                                "TabSimpankFragment",
                                "Page : $pageBerita, Id : $idBerita ===================>"
                            )
                            model.getBeritaFromRoom(pageBerita!!, idBerita!!)
//                            Log.d("TabSimpankFragment", "model.berita: ${model.beritaSimpan.observe(viewLifecycleOwner,{})}")
                        } else removePopulateData()
                    }
                })

                model.beritaSimpan.observe(viewLifecycleOwner, { listItem ->
                    if (listItem != null) populateData(listItem.toDataBeritaFromRoom(), modeAdapter)
                })
            }
            1->{
                val modeAdapter = "EVENT"
                var pageEvent : Int? = null
                var idEvent : Int? = null

                model.saved.observe(viewLifecycleOwner,{
                    if (it.isEmpty()) binding.rvSimpan.adapter = null
                    it.forEach { dataSave ->
                        if (dataSave.type.equals(modeAdapter)){
                            pageEvent = convertPageIdToPage(dataSave.pageid!!)
                            idEvent = convertPageIdToId(dataSave.pageid!!)
                            model.getEventFromRoom(pageEvent!!, idEvent!!)
                        }else binding.rvSimpan.adapter = null
                    }
                })
                model.eventSimpan.observe(viewLifecycleOwner,{listItem ->
                    if (listItem != null) populateData(listItem.toDataEventFromRoom(), modeAdapter)
                })
            }
            2->{}
            3->{}
            4->{}
            5->{}
        }
    }

    private fun removePopulateData() = with(binding){
        rvSimpan.adapter = null
    }

    private fun populateData(it: List<Any>, modeAdapter : String) = with(binding) {
        binding.rvSimpan.apply {
            layoutManager = LinearLayoutManager(activity)
//            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = ListSimpanAdapter(it, requireActivity(), modeAdapter)
            Log.d("TabSimpankFragment", "populateData: $it")
        }
    }


    companion object {}
}