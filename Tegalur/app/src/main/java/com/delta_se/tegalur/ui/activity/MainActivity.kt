package com.delta_se.tegalur.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.databinding.ActivityMainBinding
import com.delta_se.tegalur.ui.adapter.ViewPagerFragmentAdapter
import com.delta_se.tegalur.ui.fragments.*
import com.google.android.gms.dynamic.SupportFragmentWrapper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    fun initNavigation() = with(binding){
        vpMain.adapter = ViewPagerFragmentAdapter(this@MainActivity)
        vpMain.isUserInputEnabled = false
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navgation_beranda-> setNavigation(0)
                R.id.navgation_berita -> setNavigation(1)
                R.id.navgation_event -> setNavigation(2)
                R.id.navgation_simpan -> setNavigation(3)
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    fun setNavigation(index : Int): Boolean {
        binding.vpMain.currentItem = index
        return true
    }

    companion object{}
}