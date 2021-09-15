package com.hfad.wetherwithmvvm.di

import com.hfad.wetherwithmvvm.framework.ui.details.DetailsViewModel
import com.hfad.wetherwithmvvm.framework.ui.history.HistoryViewModel
import com.hfad.wetherwithmvvm.framework.ui.list_of_cities.ListOfCitiesViewModel
import com.hfad.wetherwithmvvm.model.repository.Repository
import com.hfad.wetherwithmvvm.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<Repository> {RepositoryImpl()}

    //View models
    viewModel { ListOfCitiesViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}