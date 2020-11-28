package com.delta_se.tegalur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.delta_se.tegalur.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }



}