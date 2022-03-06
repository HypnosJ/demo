package com.jacky.demohomework.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jacky.demohomework.R
import com.jacky.demohomework.data.api.RetrofitService
import com.jacky.demohomework.data.repository.MainRepository
import com.jacky.demohomework.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: InformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitService
        val mainRepository = MainRepository(retrofitService)
        vm = ViewModelProvider(
            requireActivity(),
            InformationViewModelFactory(mainRepository)
        )[InformationViewModel::class.java]

        binding.emailTv.text = vm.loginInfo.value!!.username

        val timeZones = arrayOf("TimeZone", "GMT+0", "GMT+8")
        val timeZoneSpinnerAdapter =
            ArrayAdapter(requireContext(), R.layout.spinner_timezone, timeZones)
        timeZoneSpinnerAdapter.setDropDownViewResource(R.layout.spinner_timezone)
        binding.timeZoneSpinner.adapter = timeZoneSpinnerAdapter
        binding.timeZoneSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {}
                        1 -> vm.updateUserTimeZone(0, "GMT+0")
                        2 -> vm.updateUserTimeZone(8, "GMT+8")
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}