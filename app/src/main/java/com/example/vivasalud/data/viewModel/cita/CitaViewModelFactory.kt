package com.example.vivasalud.data.viewModel.cita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vivasalud.data.repository.CitaRepository

class CitaViewModelFactory(private val repository: CitaRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitaViewModel::class.java)) {
            return CitaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}