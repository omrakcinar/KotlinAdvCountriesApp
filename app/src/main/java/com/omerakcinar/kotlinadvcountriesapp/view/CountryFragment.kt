package com.omerakcinar.kotlinadvcountriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omerakcinar.kotlinadvcountriesapp.databinding.FragmentCountryBinding
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
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
            println(countryUuid)
        }

        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.countryNameDetail.text = it.countryName
                binding.countryCapitalDetail.text = it.countryCapital
                binding.countryRegionDetail.text = it.countryRegion
                binding.countryCurrencyDetail.text = it.countryCurrency
                binding.countryLanguageDetail.text = it.countryLanguage
            }
        })
    }

}