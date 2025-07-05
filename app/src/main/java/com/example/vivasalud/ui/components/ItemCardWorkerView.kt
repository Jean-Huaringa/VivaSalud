package com.example.vivasalud.ui.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.vivasalud.R

class ItemCardWorkerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs){

    private val titleCard: TextView
    private val nameCard: TextView
    private val lastNameCard: TextView

    init {
        inflate(context, R.layout.item_card_worker, this)

        titleCard = findViewById(R.id.title_card)
        nameCard = findViewById(R.id.name_card)
        lastNameCard = findViewById(R.id.last_name_card)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ItemCardWorkerView)

            val title = typedArray.getString(R.styleable.ItemCardWorkerView_cardWorkerTitle)
            val name = typedArray.getString(R.styleable.ItemCardWorkerView_cardName)
            val lastname = typedArray.getString(R.styleable.ItemCardWorkerView_cardLastname)


            if (title != null) titleCard.text = title
            if (name != null) nameCard.text = name
            if (lastname != null) lastNameCard.text = lastname

            typedArray.recycle()
        }
    }

    fun setTitle(title: String) {
        titleCard.text = title
    }

    fun setName(name: String) {
        nameCard.text = name
    }

    fun setLastname(lastname: String) {
        lastNameCard.text = lastname
    }
}