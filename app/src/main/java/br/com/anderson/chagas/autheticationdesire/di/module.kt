package br.com.anderson.chagas.autheticationdesire.di

import br.com.anderson.chagas.autheticationdesire.service.FirebaseService
import br.com.anderson.chagas.autheticationdesire.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    single { FirebaseAuth.getInstance() }
    single { FirebaseService(get()) }
}