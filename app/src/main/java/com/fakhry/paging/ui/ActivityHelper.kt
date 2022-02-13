package com.fakhry.paging.ui

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.showToast(context: Context, message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
