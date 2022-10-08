package com.omerakcinar.kotlinadvcountriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omerakcinar.kotlinadvcountriesapp.databinding.FragmentCountryBinding
import com.omerakcinar.kotlinadvcountriesapp.util.downloadFromUrl
import com.omerakcinar.kotlinadvcountriesapp.util.placeholderProgressBar
import com.omerakcinar.kotlinadvcountriesapp.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var countryUuid = -1
    private var _binding : FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
            println(countryUuid)
        }

        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { country  ->
                binding.countryNameDetail.text = country.countryName
                binding.countryCapitalDetail.text = country.countryCapital
                binding.countryRegionDetail.text = country.countryRegion
                binding.countryCurrencyDetail.text = country.countryCurrency
                binding.countryLanguageDetail.text = country.countryLanguage
                context?.let {
                    binding.flagImageDetail.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }
            }
        })
    }

}