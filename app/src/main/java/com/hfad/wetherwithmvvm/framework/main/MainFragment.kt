package com.hfad.wetherwithmvvm.framework.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.hfad.wetherwithmvvm.R
import com.hfad.wetherwithmvvm.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainFragment : Fragment() {


    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = MainFragmentBinding.inflate(inflater,container, false )
        return binding.root
    }


    /*
    Что бы подписаться на нашу LiveData мы должны сделать Observe (наблюдатель)
     Observe - будет реагировать на то когда наша LiveData будет изменяться

     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState> {renderData(it)}
        viewModel.getLiveData().observe(viewLifecycleOwner, observer) // говорим во viewModel getDat-e  observe (наблюдать) - передаём туда viewLifecycleOwner (это по сути ссылка на нашь MainFragment) и затем передаём наш  observer
        viewModel.getWeather()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                progressBar.visibility = View.GONE
                Snackbar.make(mainView, "Success", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                progressBar.visibility = View.GONE
                Snackbar
                    .make(mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") {viewModel.getWeather()}
                    .show()
            }

        }
    }



    companion object {
        fun newInstance() = MainFragment()
    }
}