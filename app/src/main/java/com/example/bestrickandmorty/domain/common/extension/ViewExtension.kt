package com.example.bestrickandmorty.domain.common.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.searchText(data: (name: String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            data(p0.toString())
        }
    })
}
