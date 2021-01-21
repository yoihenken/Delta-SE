package com.delta_se.tegalur.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delta_se.tegalur.databinding.ActivitySearchBinding
import com.delta_se.tegalur.ui.fragments.TabSearchFragment
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
                .add("Event", TabSearchFragment::class.java)
                .add("Pariwisata", TabSearchFragment::class.java)
                .add("Kuliner", TabSearchFragment::class.java)
                .add("Oleh-oleh", TabSearchFragment::class.java)
                .add("Penginapan", TabSearchFragment::class.java)

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