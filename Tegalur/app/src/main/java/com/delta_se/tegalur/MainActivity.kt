package com.delta_se.tegalur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.data.model.DataBerita
import com.delta_se.tegalur.databinding.ActivityMainBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
import com.delta_se.tegalur.ui.fragments.BerandaFragment
import com.delta_se.tegalur.ui.fragments.BeritaFragment
import com.delta_se.tegalur.ui.fragments.EventFragment
import com.delta_se.tegalur.ui.fragments.SimpanFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentIndex = 0

    private lateinit var binding: ActivityMainBinding
    private val fragments = listOf(
        BerandaFragment(),
        BeritaFragment(),
        EventFragment(),
        SimpanFragment()
    )
    private val list = ArrayList<DataBerita>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
    }

    var activeFragment = fragments[0]

    private fun setNavigation() {
        binding.apply {
            supportFragmentManager.beginTransaction()
                .add(flWrapper.id, fragments[0], "Beranda")
                .add(flWrapper.id, fragments[1], "Berita").hide(fragments[1])
                .add(flWrapper.id, fragments[2], "Event").hide(fragments[2])
                .add(flWrapper.id, fragments[3], "Simpan").hide(fragments[3])
                .commit()
            bottom_navigation.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.navgation_beranda -> {
                        showFragment(fragments[0])
                    }
                    R.id.navgation_berita -> {
                        showFragment(fragments[1])
                    }
                    R.id.navgation_event -> {
                        showFragment(fragments[2])
                    }
                    else -> {
                        showFragment(fragments[3])
                    }
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) : Boolean{
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
        return true
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        supportFragmentManager.beginTransaction().remove(fragments[fragmentIndex]).commit()
        outState.putInt(FRAGMENT_STATE, fragmentIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        fragmentIndex = savedInstanceState.getInt(FRAGMENT_STATE)
    }

    companion object{
        private const val FRAGMENT_STATE = "fragment_state"
    }
}