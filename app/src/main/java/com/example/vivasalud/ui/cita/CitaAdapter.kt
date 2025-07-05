package com.example.vivasalud.ui.cita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vivasalud.R
import com.example.vivasalud.data.model.Cita

class CitaAdapter(private val citas: List<Cita>, private val nombrePaciente: String, private val onDeleteClick: (Cita) -> Unit) :
    RecyclerView.Adapter<CitaAdapter.CitaViewHolder>() {

    class CitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val area: TextView = itemView.findViewById(R.id.area)
        val nameDoctor: TextView = itemView.findViewById(R.id.name_doctor)
        val ubication: TextView = itemView.findViewById(R.id.ubication)
        val namePaciente: TextView = itemView.findViewById(R.id.name_paciente)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_cita, parent, false)
        return CitaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        val cita = citas[position]
        holder.area.text = "${cita.clinica} - ${cita.area}"
        holder.nameDoctor.text = "Dr. ${cita.doctor}"
        holder.ubication.text = "Clinica ${cita.clinica}"
        holder.namePaciente.text = nombrePaciente

        holder.btnDelete.setOnClickListener {
            onDeleteClick(cita)
        }
    }

    override fun getItemCount(): Int = citas.size
}