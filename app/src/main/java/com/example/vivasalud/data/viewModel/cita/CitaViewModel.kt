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

    fun seletTypeCita(type: String) {
        _cita.value = _cita.value?.copy(
            type = type
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

    fun insertCita(cita: Cita) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCita(cita)
            // Luego puedes actualizar las citas si es necesario
            getCitaByIdUser(cita.usuarioId)
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