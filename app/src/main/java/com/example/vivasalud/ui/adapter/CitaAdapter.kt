package com.example.vivasalud.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vivasalud.R
import com.example.vivasalud.data.model.Cita
import com.example.vivasalud.databinding.ItemCardCitaBinding
import com.example.vivasalud.ui.cita.OnClickListener

class CitaAdapter (
    private var citas: MutableList<Cita>,
    private var listener: OnClickListener)
    : RecyclerView.Adapter<CitaAdapter.ViewHolder>() {


    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_card_cita, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = citas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cita = citas.get(position)

        with(holder) {
            setListener(cita)
            binding.day.text = cita.day
            binding.time.text = cita.time
            binding.area.text = cita.area
            binding.nameDoctor.text = cita.doctor
            binding.ubication.text = cita.clinica
            binding.namePaciente.text = cita.name
        }

    }


    // Método para establecer las citas
    fun setCitas(citas: MutableList<Cita>) {
        this.citas = citas
        notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }

    // Método para agregar una cita
    fun add(cita: Cita) {
        citas.add(cita)
        notifyItemInserted(citas.size - 1) // Notificar que se ha agregado un nuevo ítem
    }

    // Método para actualizar una cita
    fun update(cita: Cita) {
        val index = citas.indexOfFirst { it.id == cita.id } // Asumiendo que Cita tiene un campo id
        if (index != -1) {
            citas[index] = cita
            notifyItemChanged(index) // Notificar que el ítem ha sido actualizado
        }
    }

    // Método para eliminar una cita
    fun delete(cita: Cita) {
        val index = citas.indexOfFirst { it.id == cita.id } // Buscar la cita por id
        if (index != -1) {
            citas.removeAt(index)
            notifyItemRemoved(index) // Notificar que el ítem ha sido eliminado
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCardCitaBinding.bind(view)

        fun setListener(cita: Cita) {

            with(binding.root) {
                setOnClickListener { listener.onClick(cita) }
                setOnLongClickListener {
                    listener.onDeleteStore(cita)
                    true
                }
            }

        }
    }
}
