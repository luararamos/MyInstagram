package com.example.myinstagram

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar


class LoadingButton : FrameLayout {
    private lateinit var button: Button
    private lateinit var progress: ProgressBar
    private var text: String? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs,
        defStyleAttr
    ) {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.button_loading, this)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)
        text = typeArray.getString(R.styleable.LoadingButton_text)

        button = getChildAt(0) as Button
        progress = getChildAt(1) as ProgressBar

        button.text = text
        button.isEnabled = false

        typeArray.recycle()

    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (enabled) {
            button.text = text
            button.isEnabled = true
            progress.visibility = View.VISIBLE
        } else {
            button.text = ""
            button.isEnabled = false
            progress.visibility = View.GONE
        }
    }

}

