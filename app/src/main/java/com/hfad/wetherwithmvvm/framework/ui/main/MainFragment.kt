package com.hfad.wetherwithmvvm.framework.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hfad.wetherwithmvvm.AppState
import com.hfad.wetherwithmvvm.R
import com.hfad.wetherwithmvvm.databinding.MainFragmentBinding
import com.hfad.wetherwithmvvm.framework.ui.details.DetailsFragment
import com.hfad.wetherwithmvvm.framework.ui.adapters.MainFragmentAdapter
import com.hfad.wetherwithmvvm.model.entities.Weather
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var isDataSetWorld: Boolean = false
    private val IS_WORLD_KEY = "LIST_OF_TOWNS_KEY"
    private val viewModel: MainViewModel by viewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: MainFragmentAdapter? = null
    private var isDataSetRus: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentRecyclerView.adapter = adapter

            initDataSet()
            loadDataSet()


            mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
            viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initDataSet() {
        activity?.let {
            isDataSetRus = it.getPreferences(Context.MODE_PRIVATE)
                .getBoolean(IS_WORLD_KEY,true)
        }
    }


    private fun changeWeatherDataSet() = with(binding) {
        isDataSetRus = !isDataSetRus
        loadDataSet()
    }

    private fun loadDataSet() = with(binding) {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocalSourceWorld()
            mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceRus()
            mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        saveDataSetDisk()
    }

    private fun saveDataSetDisk() {
        activity?.let {
            val preferences = it.getPreferences(Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean(IS_WORLD_KEY, isDataSetRus)
            editor.apply()
        }

    }
    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(weather: Weather) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(DetailsFragment.BUNDLE_EXTRA, weather)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, DetailsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setWeather(appState.weatherData)
                }
                mainFragmentRecyclerView.adapter = adapter
            }
            is AppState.Loading -> {
                mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
                    .show()
            }
        }
    }


    interface OnItemViewClickListener {

        fun onItemViewClick(weather: Weather)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}