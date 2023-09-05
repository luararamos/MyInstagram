package com.example.myinstagram.common.util

import android.text.Editable
import android.text.TextWatcher

class TxtWatcher(val onTexChanged: (String) -> Unit): TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTexChanged(s.toString())
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}