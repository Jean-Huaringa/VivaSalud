package com.example.vivasalud.data.viewModel.cita

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.data.repository.CitaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CitaViewModel(private val repository: CitaRepository) : ViewModel() {

    private val _citas = MutableLiveData<List<Cita>>()
    val citas: LiveData<List<Cita>> get() = _citas

    private val _cita = MutableLiveData(
        Cita(
            id = 0,
            type = "",
            area = "",
            clinica = "",
            doctor = "",
            usuarioId = 0
        )
    )

    val cita: LiveData<Cita> get() = _cita

    fun seletTypeCita(type: String, id: Int) {
        _cita.value = _cita.value?.copy(
            type = type,
            usuarioId = id
        )
    }

    fun seletDoctorCita(docto: String, area: String, clinica: String) {
        _cita.value = _cita.value?.copy(
            doctor = docto,
            area = area,
            clinica = clinica
        )
    }

    fun seletLugarCita(clinica: String) {
        _cita.value = _cita.value?.copy(
            clinica = clinica
        )
    }

    fun getCitaByIdUser(usuarioId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val citasUsuario = repository.getCitaByIdUser(usuarioId)
            withContext(Dispatchers.Main) {
                _citas.value = citasUsuario
            }
        }
    }

    fun insertCita() {
        val citaToInsert = _cita.value?.copy(id = 0)

        citaToInsert?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertCita(it)
                getCitaByIdUser(it.usuarioId)
            }
        }
    }

    fun updateCita(cita: Cita) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCita(cita)
            (cita.usuarioId)
        }
    }

    fun deleteCita(cita: Cita) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCita(cita)
            getCitaByIdUser(cita.usuarioId)
        }
    }

}