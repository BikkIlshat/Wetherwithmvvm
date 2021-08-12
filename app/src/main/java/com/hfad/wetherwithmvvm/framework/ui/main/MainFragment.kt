package com.hfad.wetherwithmvvm.framework.ui.main

import androidx.fragment.app.Fragment
import com.hfad.wetherwithmvvm.model.entities.Weather

class MainFragment : Fragment() {
    interface OnItemViewClickListener {
        fun onItemViewClick(weather: Weather)
    }

}