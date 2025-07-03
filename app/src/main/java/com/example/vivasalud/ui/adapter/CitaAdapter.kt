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
