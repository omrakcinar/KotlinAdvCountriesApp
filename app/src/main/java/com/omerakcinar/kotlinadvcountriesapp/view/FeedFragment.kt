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
import com.omerakcinar.kotlinadvcountriesapp.R
import com.omerakcinar.kotlinadvcountriesapp.adapter.CountryAdapter
import com.omerakcinar.kotlinadvcountriesapp.databinding.FragmentFeedBinding
import com.omerakcinar.kotlinadvcountriesapp.model.Country
import com.omerakcinar.kotlinadvcountriesapp.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        countryListRecycler.layoutManager = LinearLayoutManager(context)
        countryListRecycler.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            countryListRecycler.visibility = View.GONE
            countryErrorTV.visibility = View.GONE
            countryLoadingBar.visibility = View.VISIBLE
            viewModel.refreshFromApi()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                countryListRecycler.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryErrorTV.visibility = View.VISIBLE
                    countryListRecycler.visibility = View.GONE
                    countryLoadingBar.visibility = View.GONE
                } else {
                    countryErrorTV.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryLoadingBar.visibility = View.VISIBLE
                    countryListRecycler.visibility = View.GONE
                    countryErrorTV.visibility = View.GONE
                } else {
                    countryLoadingBar.visibility = View.GONE
                }
            }
        })

    }




}