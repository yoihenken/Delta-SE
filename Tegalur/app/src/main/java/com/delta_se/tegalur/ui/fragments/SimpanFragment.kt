package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataSave
import com.delta_se.tegalur.databinding.FragmentSimpanBinding
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

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

        setNavigation()
    }

    private fun setNavigation(){
        val adapter = FragmentPagerItemAdapter( childFragmentManager, FragmentPagerItems.with(requireContext())
            .add("Berita", TabSimpankFragment::class.java)
            .add("Event", TabSimpankFragment::class.java)
            .add("Pariwisata", TabSimpankFragment::class.java)
            .add("Kuliner", TabSimpankFragment::class.java)
            .add("Oleh-oleh", TabSimpankFragment::class.java)
            .add("Penginapan", TabSimpankFragment::class.java)
            .create()
        )

        binding.apply {
            viewPagerSimpan.adapter = adapter
            viewPagerSimpan.setCurrentItem(0, true)
            tabSave.setViewPager(viewPagerSimpan)
        }
    }

    companion object {}
}