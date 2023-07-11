package com.ewide.test.mikkel.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.*

fun EditText.startTyping(onFinishTyping: (String) -> Unit) {
    this.addTextChangedListener(
        object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            private var timer: Timer = Timer()
            private val DELAY_TYPING: Long = 1000

            override fun afterTextChanged(s: Editable) {
                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            onFinishTyping(s.toString())
                        }
                    }, DELAY_TYPING)
            }
        }
    )
}