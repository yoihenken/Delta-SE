package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.delta_se.tegalur.R
import com.delta_se.tegalur.data.response.ListItem
import com.delta_se.tegalur.databinding.FragmentEventBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter
import com.delta_se.tegalur.ui.adapter.ListEventAdapter
import com.delta_se.tegalur.utils.Helpers.toDataEvent

class EventFragment : Fragment() {

    private lateinit var binding  : FragmentEventBinding
    private val model: EventViewModel by viewModels()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEventBinding.bind(view)

        model.getEvent(page)
        model.event.observe(viewLifecycleOwner, {
            populateDataEvent(it)
        })
    }

    private fun populateDataEvent(it: List<ListItem>?) = with(binding){
        rvEvent.apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = ListEventAdapter(it?.toDataEvent() ?: listOf(), context)

        }
    }

}