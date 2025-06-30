package com.example.vivasalud


import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
class ItemCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs) {

    private val imgCard: ImageView
    private val txtTitle: TextView

    init {
        inflate(context, R.layout.item_card, this)

        // Opcional: personaliza el CardView
        radius = 30f
        cardElevation = 8f
        useCompatPadding = true

        imgCard = findViewById(R.id.img_card)
        txtTitle = findViewById(R.id.title_card)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ItemCardView)

            val title = typedArray.getString(R.styleable.ItemCardView_cardTitle)
            val imageRes = typedArray.getResourceId(R.styleable.ItemCardView_cardImage, -1)

            if (title != null) txtTitle.text = title
            if (imageRes != -1) imgCard.setImageResource(imageRes)

            typedArray.recycle()
        }
    }

    fun setTitle(text: String) { txtTitle.text = text }
    fun setImage(resId: Int) { imgCard.setImageResource(resId) }
}