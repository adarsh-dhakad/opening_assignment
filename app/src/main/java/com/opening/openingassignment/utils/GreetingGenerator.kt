package com.opening.openingassignment.utils
import java.text.SimpleDateFormat
import java.util.Date

fun generateGreeting(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val currentDate = Date(currentTimeMillis)
    val sdf = SimpleDateFormat("HH:mm")
    val currentTimeString = sdf.format(currentDate)
    val hour = currentTimeString.substring(0, 2).toInt()
    val minute = currentTimeString.substring(3).toInt()
    return when {
        hour in 5 until 12 -> "Good morning!"
        hour in 12 until 18 -> "Good afternoon!"
        else -> "Good evening!"
    }
}
