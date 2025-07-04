package com.example.vivasalud.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vivasalud.data.model.Usuario
import com.example.vivasalud.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistroViewModel(private val repository: UserRepository): ViewModel() {

    private val _usuarioLogueado = MutableLiveData<Usuario?>()
    val usuarioLogueado: LiveData<Usuario?> get() = _usuarioLogueado

    private val _usuario = MutableLiveData(
        Usuario(
            id = null,
            name = "",
            paternalSurname = "",
            maternalSurname = "",
            sexo = "",
            seguro = "",
            pais = "",
            department = "",
            province = "",
            district = "",
            home = "",
            typeDocument = "",
            numberDocument = "",
            birthdate = "",
            password = ""
        )
    )
    val usuario: LiveData<Usuario> get() = _usuario

    fun filtroRegistroUno(typeDoc: String, numDoc: String, brthDate: String) {
        _usuario.value = _usuario.value?.copy(
            typeDocument = typeDoc,
            numberDocument = numDoc,
            birthdate = brthDate
        )
    }

    fun filtroRegistroDos(name: String, apePat: String, apeMat: String, sexo: String, seguro: String, pais: String, departamento: String, provincia: String, distrito: String, home: String){
        _usuario.value = _usuario.value?.copy(
            name = name,
            paternalSurname = apePat,
            maternalSurname = apeMat,
            sexo = sexo,
            seguro = seguro,
            pais = pais,
            department = departamento,
            province = provincia,
            district = distrito,
            home = home
        )
    }

    fun filtroRegistroTres(pass: String) {
        _usuario.value = _usuario.value?.copy(
            password = pass,
        )
    }
    fun registrarUsuarioEnBD() {
        _usuario.value?.let { usuario ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertUser(usuario)
            }
        }
    }

    fun login(typeDocuent: String, document: String, password: String, onResult: (Usuario?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val usuario = repository.getUserByLogin(typeDocuent, document, password)
            withContext(Dispatchers.Main) {
                if (usuario != null) {
                    _usuarioLogueado.value = usuario
                    onResult(usuario)
                } else {
                    onResult(null)
                }
            }
        }
    }
    fun setUsuarioLogueado(usuario: Usuario) {
        _usuarioLogueado.value = usuario
    }
    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(usuario)
            _usuarioLogueado.postValue(usuario)
        }
    }

    fun eliminarUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(usuario)
        }
    }

    fun limpiarSesion() {
        _usuarioLogueado.postValue(null)
    }

}