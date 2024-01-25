package com.opening.openingassignment.di

import com.opening.openingassignment.viewmodel.DashBoardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { DashBoardViewModel(get()) }
}
