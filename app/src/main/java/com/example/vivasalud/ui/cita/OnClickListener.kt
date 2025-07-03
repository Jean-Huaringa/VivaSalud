package com.example.vivasalud.ui.cita

import com.example.vivasalud.data.model.Cita

interface OnClickListener {
    fun onClick(cita: Cita)
    fun onDeleteStore(cita: Cita)
}