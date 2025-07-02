package com.example.vivasalud.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView

class CustomAutoCompleteTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatAutoCompleteTextView(context, attrs) {

    override fun performClick(): Boolean {
        showDropDown()
        return super.performClick()
    }

    override fun onTextContextMenuItem(id: Int): Boolean{
        return false
    }
}