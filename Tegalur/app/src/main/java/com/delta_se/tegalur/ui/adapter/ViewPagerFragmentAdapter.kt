package com.delta_se.tegalur.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.delta_se.tegalur.ui.fragments.BerandaFragment
import com.delta_se.tegalur.ui.fragments.BeritaFragment
import com.delta_se.tegalur.ui.fragments.EventFragment
import com.delta_se.tegalur.ui.fragments.SimpanFragment

class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return BerandaFragment()
            1 -> return BeritaFragment()
            2 -> return EventFragment()
            3 -> return SimpanFragment()
        }
        return BerandaFragment()
    }
}