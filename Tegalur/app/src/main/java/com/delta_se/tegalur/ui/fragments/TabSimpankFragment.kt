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

        var index = FragmentPagerItem.getPosition(arguments)
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
                var page: Int? = null
                var id: Int? = null

                model.saved.observe(viewLifecycleOwner, {
                    Log.d("TabSimpankFragment", "model.saved: ${it}")
                    it.forEach { dataSave ->
                        Log.d("TabSimpankFragment", "dataSave: $dataSave")
                        if (dataSave.type.equals(modeAdapter)) {
                            page = convertPageIdToPage(dataSave.pageid!!)
                            id = convertPageIdToId(dataSave.pageid!!)
                            model.getBeritaFromRoom(
                                convertPageIdToPage(dataSave.pageid!!),
                                convertPageIdToId(dataSave.pageid!!)
                            )
                            Log.d("TabSimpankFragment", "model.berita: ${model.beritaSimpan.toString()}")
                        }
                    }
                })

                Log.d("TabSimpankFragment", "isNull:")
                model.beritaSimpan.observe(viewLifecycleOwner, { listItem ->
                    if (listItem != null) populateData(listItem.toDataBeritaFromRoom(page, id), modeAdapter)
                })
            }
            1->{
                val modeAdapter = "EVENT"
                var page : Int? = null
                var id : Int? = null

                model.saved.observe(viewLifecycleOwner,{
                    it.forEach { dataSave ->
                        if (dataSave.type.equals(modeAdapter)){
                            page = convertPageIdToPage(dataSave.pageid!!)
                            id = convertPageIdToId(dataSave.pageid!!)
                            model.getEventFromRoom(page!!, id!!)
                        }
                    }
                })
                model.eventSimpan.observe(viewLifecycleOwner,{listItem ->
                    if (listItem != null) populateData(listItem.toDataEventFromRoom(page, id), modeAdapter)
                })
            }
            2->{}
            3->{}
            4->{}
            5->{}
        }
    }

    private fun populateData(it: List<Any>, modeAdapter : String) = with(binding) {
        binding.rvSimpan.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = ListSimpanAdapter(it, context, modeAdapter)
            Log.d("TabSimpankFragment", "populateData: $it")
        }
    }


    companion object {}
}