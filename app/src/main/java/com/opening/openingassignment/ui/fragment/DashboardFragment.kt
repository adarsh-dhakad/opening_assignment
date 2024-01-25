package com.opening.openingassignment.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.opening.network.data.response.GenericResponse
import com.opening.network.model.DashBoardResponse
import com.opening.network.model.TopLink
import com.opening.openingassignment.R
import com.opening.openingassignment.databinding.FragmentDashboardBinding
import com.opening.openingassignment.utils.LineChartGenerator
import com.opening.openingassignment.ui.model.TrendingItem
import com.opening.openingassignment.utils.copyToClipboard
import com.opening.openingassignment.utils.generateGreeting
import com.opening.openingassignment.viewmodel.DashBoardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashboardFragment : Fragment() {
    val viewModel: DashBoardViewModel by viewModel()
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDashBoardData()
        Log.d("devKey" , "calling");
        viewModel.dashBoardData.observe(viewLifecycleOwner) {
            when (it) {
                is GenericResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    setUpUI(it.data)
                }

                is GenericResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                else -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setUpUI(data: DashBoardResponse) {
        val chart = LineChartGenerator()
            .ConvertToCartData(data.data.overall_url_chart,data.startTime)
        binding.tvGreetings.text = generateGreeting()
        binding.anyChartView.setProgressBar(binding.progressBar)
        binding.anyChartView.setChart(chart)
        val list = ArrayList<TrendingItem>()
        list.add(TrendingItem(R.drawable.ic_click, data.today_clicks.toString(), "Today Clicks"))
        list.add(TrendingItem(R.drawable.ic_location, data.top_location, "Top Location"))
        list.add(TrendingItem(R.drawable.baseline_cell_tower_24, data.top_source, "Top Source"))
        setUpRecyclerView(list)
        setUpTopLinksAdapter(data.data.top_links)

        binding.tvTopLinks.setOnClickListener {
            setUpTopLinksAdapter(data.data.top_links)
            // set background drawable
            binding.tvTopLinks.setBackgroundResource(R.drawable.button_background)
            // remove background drawable
            binding.tvRecentLinks.setBackgroundResource(0)
            binding.tvTopLinks.setTextColor(resources.getColor(R.color.white))
            binding.tvTopLinks.setTypeface(null, Typeface.BOLD)
            binding.tvRecentLinks.setTextColor(resources.getColor(R.color.black))
            binding.tvRecentLinks.setTypeface(null, Typeface.NORMAL)

        }
        binding.tvRecentLinks.setOnClickListener {
            // set background drawable
            binding.tvRecentLinks.setBackgroundResource(R.drawable.button_background)
            // set text color
            binding.tvRecentLinks.setTextColor(resources.getColor(R.color.white))
            binding.tvRecentLinks.setTypeface(null, Typeface.BOLD)
            // remove background drawable
            binding.tvTopLinks.setBackgroundResource(0)
            binding.tvTopLinks.setTextColor(resources.getColor(R.color.black))
            binding.tvTopLinks.setTypeface(null, Typeface.NORMAL)
            setUpTopLinksAdapter(data.data.recent_links)
        }

    }

    private fun setUpTopLinksAdapter(list: List<TopLink>){
        val listener : (String) -> Unit = {
            copyToClipboard(it, requireContext())
        }
        val adapter = TopLinksAdapter(listener)
        adapter.addItems(list)
        val recyclerView: RecyclerView = binding.rvTopLinks
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }

    private fun setUpRecyclerView(list: ArrayList<TrendingItem>){
        val adapter = TrendingItemAdapter()
        adapter.addItems(list)
        val recyclerView: RecyclerView = binding.rvDashboard
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
    }
}