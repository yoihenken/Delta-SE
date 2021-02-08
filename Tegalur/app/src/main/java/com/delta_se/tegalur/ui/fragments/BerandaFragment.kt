package com.delta_se.tegalur.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.R
import com.delta_se.tegalur.ui.activity.SearchActivity
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.databinding.FragmentBerandaBinding
import com.delta_se.tegalur.databinding.LayoutCategoryBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
import com.delta_se.tegalur.utils.Helpers.toDataBerita
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import kotlinx.android.synthetic.main.fragment_beranda.*

class BerandaFragment : Fragment() {

    private lateinit var binding: FragmentBerandaBinding
    private lateinit var bindingLayoutCategory : LayoutCategoryBinding
    private val model: BerandaViewModel by viewModels()
    private lateinit var modelBerita : BeritaViewModel
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        modelBerita = BeritaViewModel()
        modelBerita.getSavedNews(requireActivity().application, requireActivity())
        model.getBerita(page)

        model.berita.observe(viewLifecycleOwner) {
            Log.d("BerandaFragment", "Berita : $it")
            populateDataBerita(it)
        }

        bindingLayoutCategory.apply {
            categoryPariwisata.setOnClickListener {
                val intentIndex = 0
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }
            categoryKuliner.setOnClickListener {
                val intentIndex = 1
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }
            categoryOleh.setOnClickListener {
                val intentIndex = 2
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }
            categoryPenginapan.setOnClickListener {
                val intentIndex = 3
                val moveWithIntent = Intent(activity, SearchActivity::class.java)
                moveWithIntent.putExtra(SearchActivity.EXTRA_DATA, intentIndex)
                activity?.startActivity(moveWithIntent)
            }

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.direction = SwipyRefreshLayoutDirection.BOTTOM

                if (swipeRefresh.isRefreshing){
                    page++
                    model.getBerita(page)
                    Log.d("BerandaFragment", "onViewCreated: Load More")
                }
                swipeRefresh.isRefreshing = false
            }

            nestedScrollView.setOnScrollChangeListener{ v: NestedScrollView?, _: Int, scrollY: Int, _: Int, _: Int -> val diff = v!!.getChildAt(0)!!.measuredHeight - v.measuredHeight
                if (diff - scrollY < 7000 && swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
            }
        }
    }
    private fun populateDataBerita(it: List<ListItem>?) = with(binding) {
        Log.d("BerandaFragment", "populateDataBerita: $page")
        rvBeranda.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = ListBeritaAdapter(it?.toDataBerita() ?: listOf(), context, modelBerita)
        }
    }

    companion object {}
}