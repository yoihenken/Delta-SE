package com.delta_se.tegalur.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.R
import com.delta_se.tegalur.SearchActivity
import com.delta_se.tegalur.data.dummy.DataDummy
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.data.response.ListResponse
import com.delta_se.tegalur.databinding.FragmentBerandaBinding
import com.delta_se.tegalur.databinding.LayoutCategoryBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
import com.delta_se.tegalur.utils.Helpers.toDataBerita
import kotlinx.android.synthetic.main.fragment_beranda.*

class BerandaFragment : Fragment() {

    private lateinit var binding: FragmentBerandaBinding
    private lateinit var bindingLayoutCategory : LayoutCategoryBinding
    private val list = ArrayList<DataBerita>()
    private val model: BerandaViewModel by viewModels()
    private var page = 1
//    private var isLoading = false

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
        bindingLayoutCategory = LayoutCategoryBinding.bind(view)
        model.getBerita(page)

        binding.rvBeranda.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        model.berita.observe(viewLifecycleOwner, {
            Log.d("BerandaFragment", "Berita : $it")
            populateDataBerita(it)
        })


        bindingLayoutCategory.apply {
            categoryPariwisata.setOnClickListener {
                val intentIndex = 2
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }
            categoryKuliner.setOnClickListener {
                val intentIndex = 3
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }
            categoryOleh.setOnClickListener {
                val intentIndex = 4
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }
            categoryPenginapan.setOnClickListener {
                val intentIndex = 5
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }
            nestedScrollView.setOnScrollChangeListener{ v: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int -> val diff = v!!.getChildAt(0)!!.measuredHeight - v.measuredHeight
                if (diff - scrollY < 1000) {
                    page++
                    model.getBerita(page)
                    Log.d("BerandaFragment", "onViewCreated: Load More")
                }
            }

        }
    }

    private fun populateDataBerita(it: List<ListItem>?) = with(binding) {
        Log.d("BerandaFragment", "populateDataBerita: $page")
        rvBeranda.apply {
            adapter = ListBeritaAdapter(it?.toDataBerita() ?: listOf(), context)
        }
    }

    companion object {}
}