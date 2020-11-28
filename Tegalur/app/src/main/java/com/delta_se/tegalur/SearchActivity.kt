package com.delta_se.tegalur

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.delta_se.tegalur.databinding.ActivitySearchBinding
import com.delta_se.tegalur.ui.adapter.ListCategoryAdapter
import com.delta_se.tegalur.ui.fragments.TabSearchFragment
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*


class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
    }

    private fun setNavigation(){
        val intentIndex = intent.getIntExtra(EXTRA_DATA, -1)
        var adapter = FragmentPagerItemAdapter(
            supportFragmentManager, FragmentPagerItems.with(this)
                .add("Berita", TabSearchFragment::class.java)
                .add("Pariwisata", TabSearchFragment::class.java)
                .create()
        )

        binding.apply {
            viewPager.adapter = adapter
            viewPager.setCurrentItem(intentIndex,true)
            tabSearch.setViewPager(viewPager)

            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}