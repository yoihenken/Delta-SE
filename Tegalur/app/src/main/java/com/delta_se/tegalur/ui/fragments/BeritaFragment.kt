package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.databinding.FragmentBeritaBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
import com.delta_se.tegalur.utils.Helpers.toDataBerita
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection

class BeritaFragment : Fragment() {

    private lateinit var binding: FragmentBeritaBinding
    private val list = ArrayList<DataBerita>()
    private val model: BeritaViewModel by viewModels()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        model.getBerita(page)
        model.berita.observe(viewLifecycleOwner, {
            Log.d("BeritaFragment", "Berita : $it")
            populateDataBerita(it)
        })

        binding.apply {
            sweepBerita.setOnRefreshListener {
                sweepBerita.direction = SwipyRefreshLayoutDirection.BOTTOM

                if (sweepBerita.isRefreshing){
                    page++
                    model.getBerita(page)
                }
            }
            nestedScrollViewBerita.setOnScrollChangeListener { view: NestedScrollView, _:Int, scrollY: Int, _:Int, _:Int  -> val diff = view.getChildAt(0)!!.measuredHeight - view.measuredHeight
            if (diff - scrollY<8000 && sweepBerita.isRefreshing) sweepBerita.isRefreshing = false
            }
        }
    }

    private fun populateDataBerita(it: List<ListItem>?) = with(binding){
        rvBerita.apply {
            Log.d("Berita Fragment", "populateDataBerita: $page")
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = ListBeritaAdapter(it?.toDataBerita() ?: listOf(), context)
        }
    }

    companion object {}
}