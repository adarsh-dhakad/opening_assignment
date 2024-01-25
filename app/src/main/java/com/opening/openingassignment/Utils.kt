package com.opening.openingassignment

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateConverter(timestampStr: String):String {
        val instant = Instant.parse(timestampStr)

        // Convert Instant to LocalDateTime in a specific time zone (UTC in this case)

        // Convert Instant to LocalDateTime in a specific time zone (UTC in this case)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))

        // Format the LocalDateTime

        // Format the LocalDateTime
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val formattedDate = dateTime.format(formatter)

        return formattedDate
    }
}