package com.hfad.wetherwithmvvm.framework.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.hfad.wetherwithmvvm.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainFragment : Fragment() {


    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    /*
    Что бы подписаться на нашу LiveData мы должны сделать Observe (наблюдатель)
     Observe - будет реагировать на то когда наша LiveData будет изменяться

     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<Any> {renderData(it)}
        viewModel.getData().observe(viewLifecycleOwner, observer) // говорим во viewModel getDat-e  observe (наблюдать) - передаём туда viewLifecycleOwner (это по сути ссылка на нашь MainFragment) и затем передаём наш  observer
    }

    private fun renderData(data: Any) {
        Toast.makeText(context, "data", Toast.LENGTH_LONG).show()
    }



    companion object {
        fun newInstance() = MainFragment()
    }
}