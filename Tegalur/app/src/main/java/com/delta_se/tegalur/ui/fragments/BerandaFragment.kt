package com.delta_se.tegalur.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delta_se.tegalur.DataBerita
import com.delta_se.tegalur.R
import com.delta_se.tegalur.databinding.FragmentBerandaBinding
import com.delta_se.tegalur.ui.adapter.ListBeritaAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BerandaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BerandaFragment : Fragment() {

    private lateinit var binding: FragmentBerandaBinding
    private val list = ArrayList<DataBerita>()
    private var adapter: RecyclerView.Adapter<ListBeritaAdapter.ListViewHolder>? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    private fun getDataBerita() : ArrayList<DataBerita>{
        var dataBerita = DataBerita(
                "Kasus Covid-19 Kota Tegal Capai 1.043, 49 di Antaranya Meninggal Dunia",
                "https://asset.kompas.com/crops/w_yzPmf5QovGja7k5Ut8bbZAaI8=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb9c8875d1a.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

        dataBerita = DataBerita(
                "Kasus Covid-19 Melonjak, GOR dan Rusunawa di Tegal Jadi Tempat Isolasi Pasien",
                "https://asset.kompas.com/crops/XEJpFatHzpPZFfF8RyECq2chxNk=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb7cbd12bba.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

        dataBerita = DataBerita(
                "Kasus Covid-19 Kota Tegal Capai 1.043, 49 di Antaranya Meninggal Dunia",
                "https://asset.kompas.com/crops/w_yzPmf5QovGja7k5Ut8bbZAaI8=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb9c8875d1a.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

        dataBerita = DataBerita(
                "Kasus Covid-19 Melonjak, GOR dan Rusunawa di Tegal Jadi Tempat Isolasi Pasien",
                "https://asset.kompas.com/crops/XEJpFatHzpPZFfF8RyECq2chxNk=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb7cbd12bba.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

        dataBerita = DataBerita(
                "Kasus Covid-19 Kota Tegal Capai 1.043, 49 di Antaranya Meninggal Dunia",
                "https://asset.kompas.com/crops/w_yzPmf5QovGja7k5Ut8bbZAaI8=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb9c8875d1a.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

        dataBerita = DataBerita(
                "Kasus Covid-19 Melonjak, GOR dan Rusunawa di Tegal Jadi Tempat Isolasi Pasien",
                "https://asset.kompas.com/crops/XEJpFatHzpPZFfF8RyECq2chxNk=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb7cbd12bba.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

        dataBerita = DataBerita(
                "Kasus Covid-19 Kota Tegal Capai 1.043, 49 di Antaranya Meninggal Dunia",
                "https://asset.kompas.com/crops/w_yzPmf5QovGja7k5Ut8bbZAaI8=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb9c8875d1a.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

        dataBerita = DataBerita(
                "Kasus Covid-19 Melonjak, GOR dan Rusunawa di Tegal Jadi Tempat Isolasi Pasien",
                "https://asset.kompas.com/crops/XEJpFatHzpPZFfF8RyECq2chxNk=/0x0:0x0/230x153/data/photo/2020/11/23/5fbb7cbd12bba.jpg",
                "23/11/2020",
                false
        )
        list.add(dataBerita)

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

        getDataBerita()

        binding.rvBeranda.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = ListBeritaAdapter(list)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BerandaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BerandaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}