package com.example.vivasalud.ui.user

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.vivasalud.R
import com.example.vivasalud.data.viewModel.RegistroViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InfoUserFragment : Fragment() {

    private val registroViewModel: RegistroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info_user, container, false)
    }

    private lateinit var btnActualizar: MaterialButton
    private lateinit var btnEliminar: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = view.findViewById<TextInputEditText>(R.id.etName)
        val etApePat = view.findViewById<TextInputEditText>(R.id.etApePat)
        val etApeMat = view.findViewById<TextInputEditText>(R.id.etApeMat)
        val etSexo = view.findViewById<TextInputEditText>(R.id.etSexo)
        val etSeguro = view.findViewById<TextInputEditText>(R.id.etSeguro)
        val etPais = view.findViewById<TextInputEditText>(R.id.etPais)
        val etDepartamento = view.findViewById<TextInputEditText>(R.id.etDepartamento)
        val etProvincia = view.findViewById<TextInputEditText>(R.id.etProvincia)
        val etDistrito = view.findViewById<TextInputEditText>(R.id.etDistrito)
        val etDireccion = view.findViewById<TextInputEditText>(R.id.etDireccion)
        val etTypeDocument = view.findViewById<TextInputEditText>(R.id.etTypeDocument)
        val etNumDocument = view.findViewById<TextInputEditText>(R.id.etNumDocument)
        val etBirthday = view.findViewById<TextInputEditText>(R.id.etBirthday)

        registroViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                etName.setText(it.name)
                etApePat.setText(it.paternalSurname)
                etApeMat.setText(it.maternalSurname)
                etSexo.setText(it.sexo)
                etSeguro.setText(it.seguro)
                etPais.setText(it.pais)
                etDepartamento.setText(it.department)
                etProvincia.setText(it.province)
                etDistrito.setText(it.district)
                etDireccion.setText(it.home)
                etTypeDocument.setText(it.typeDocument)
                etNumDocument.setText(it.numberDocument)
                etBirthday.setText(it.birthdate)
            }
        }

        btnActualizar = view.findViewById(R.id.btnActualizar)

        btnActualizar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Actualizar Perfil")
                .setMessage("¿Estás seguro de que deseas actualizar tu perfil?")
                .setPositiveButton("Sí") { _, _ ->
                    val usuarioActual = registroViewModel.usuarioLogueado.value
                    if (usuarioActual != null) {
                        val usuarioActualizado = usuarioActual.copy(
                            name = etName.text.toString(),
                            paternalSurname = etApePat.text.toString(),
                            maternalSurname = etApeMat.text.toString(),
                            sexo = etSexo.text.toString(),
                            seguro = etSeguro.text.toString(),
                            pais = etPais.text.toString(),
                            department = etDepartamento.text.toString(),
                            province = etProvincia.text.toString(),
                            district = etDistrito.text.toString(),
                            home = etDireccion.text.toString(),
                            typeDocument = etTypeDocument.text.toString(),
                            numberDocument = etNumDocument.text.toString(),
                            birthdate = etBirthday.text.toString()
                        )

                        registroViewModel.actualizarUsuario(usuarioActualizado)
                        Toast.makeText(requireContext(), "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.homeFragment)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        btnEliminar = view.findViewById(R.id.btnEliminar)

        btnEliminar.setOnClickListener {
            val usuarioActual = registroViewModel.usuarioLogueado.value
            if (usuarioActual != null) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Eliminar perfil")
                    .setMessage("¿Estás seguro de que deseas eliminar tu perfil?")
                    .setPositiveButton("Sí") { _, _ ->
                        registroViewModel.eliminarUsuario(usuarioActual)
                        registroViewModel.limpiarSesion()
                        Toast.makeText(requireContext(), "Perfil eliminado", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.logInFragment)
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
        }

    }

    
}