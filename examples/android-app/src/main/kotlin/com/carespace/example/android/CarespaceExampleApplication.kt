package com.carespace.example.android

import android.app.Application
import com.carespace.sdk.android.CarespaceAndroidClient
import com.carespace.sdk.android.AuthViewModel
import com.carespace.sdk.android.UsersViewModel
import com.carespace.sdk.android.ClientsViewModel
import com.carespace.sdk.android.ProgramsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CarespaceExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@CarespaceExampleApplication)
            modules(appModule)
        }
    }

    private val appModule = module {
        // Carespace Client
        single { 
            CarespaceAndroidClient.forDevelopment(get())
        }
        
        // ViewModels
        viewModel { AuthViewModel(get()) }
        viewModel { UsersViewModel(get()) }
        viewModel { ClientsViewModel(get()) }
        viewModel { ProgramsViewModel(get()) }
    }
}