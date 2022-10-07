package com.omerakcinar.kotlinadvcountriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.omerakcinar.kotlinadvcountriesapp.adapter.CountryAdapter
import com.omerakcinar.kotlinadvcountriesapp.databinding.FragmentFeedBinding
import com.omerakcinar.kotlinadvcountriesapp.model.Country
import com.omerakcinar.kotlinadvcountriesapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        binding.countryListRecycler.layoutManager = LinearLayoutManager(context)
        binding.countryListRecycler.adapter = countryAdapter

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.countryListRecycler.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.countryErrorTV.visibility = View.VISIBLE
                    binding.countryListRecycler.visibility = View.GONE
                    binding.countryLoadingBar.visibility = View.GONE
                } else {
                    binding.countryErrorTV.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.countryLoadingBar.visibility = View.VISIBLE
                    binding.countryListRecycler.visibility = View.GONE
                    binding.countryErrorTV.visibility = View.GONE
                } else {
                    binding.countryLoadingBar.visibility = View.GONE
                }
            }
        })

    }




}