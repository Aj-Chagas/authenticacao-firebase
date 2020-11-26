package br.com.anderson.chagas.autheticationdesire.di

import br.com.anderson.chagas.autheticationdesire.repository.RegistrationDataRepository
import br.com.anderson.chagas.autheticationdesire.service.FirebaseService
import br.com.anderson.chagas.autheticationdesire.viewmodel.LoginViewModel
import br.com.anderson.chagas.autheticationdesire.viewmodel.RegistrationDataViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationDataViewModel(get()) }
    single { RegistrationDataRepository(get()) }
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseService(get()) }
}