package com.aslibyr.countrieskotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aslibyr.countrieskotlin.adapter.CountryAdapter
import com.aslibyr.countrieskotlin.databinding.FragmentFeedBinding
import com.aslibyr.countrieskotlin.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter = countryAdapter

        observeLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.countryList.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            binding.countryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }


    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        }
        viewModel.countryError.observe(viewLifecycleOwner) { error ->
            error?.let {
                if (it) {
                    binding.countryError.visibility = View.VISIBLE
                } else {
                    binding.countryError.visibility = View.GONE
                }
            }
        }
        viewModel.countryLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                } else {
                    binding.countryLoading.visibility = View.GONE

                }
            }
        }
    }
}