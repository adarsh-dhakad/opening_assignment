package com.opening.openingassignment.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast

fun copyToClipboard(text: CharSequence , context: Context) {
        try {
            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("akc no.", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "acknowledgement number copied", Toast.LENGTH_SHORT)
                .show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error while copying number", Toast.LENGTH_SHORT)
                .show()
        }
    }