package com.jacky.demohomework.ui.information

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jacky.demohomework.R
import com.jacky.demohomework.data.api.RetrofitService
import com.jacky.demohomework.data.repository.MainRepository
import com.jacky.demohomework.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: InformationViewModel
    private lateinit var informationAdapter: InformationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        val retrofitService = RetrofitService
        val mainRepository = MainRepository(retrofitService)
        vm = ViewModelProvider(
            requireActivity(),
            InformationViewModelFactory(mainRepository)
        )[InformationViewModel::class.java]

        vm.getInformationList()

        vm.informationList.observe(viewLifecycleOwner) {
            informationAdapter = InformationListAdapter(it)
            binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
            binding.recyclerView.adapter = informationAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_information, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                val navController = findNavController(this)
                navController.navigate(R.id.settings_fragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}