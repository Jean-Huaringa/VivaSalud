package com.example.vivasalud.ui.medico

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vivasalud.R
import com.example.vivasalud.data.model.Medico


class MedicoAdapter(
    private val medicos: List<Medico>,
    private val onClick: (Medico) -> Unit
) : RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_worker, parent, false) // Aquí se infla el layout
        return MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {
        val medico = medicos[position]
        holder.bind(medico, onClick)
    }

    override fun getItemCount(): Int = medicos.size

    class MedicoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutContainer: LinearLayout = itemView.findViewById(R.id.layoutContainer) // Asegúrate de poner un ID al LinearLayout
        private val titleCard: TextView = itemView.findViewById(R.id.title_card)
        private val nameCard: TextView = itemView.findViewById(R.id.name_card)
        private val lastNameCard: TextView = itemView.findViewById(R.id.last_name_card)

        fun bind(medico: Medico, onClick: (Medico) -> Unit) {
            // Asignamos los valores de cada médico
            titleCard.text = medico.especialidad
            nameCard.text = medico.nombre
            lastNameCard.text = medico.apellidoPat

            // Configuramos el clic
            layoutContainer.setOnClickListener {
                onClick(medico) // Aquí se pasa el médico al listener
            }
        }
    }
}