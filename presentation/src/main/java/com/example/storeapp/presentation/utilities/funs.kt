package com.example.storeapp.presentation.utilities

import android.text.Editable
import android.view.View
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.storeapp.CURRENCY_FORMAT_PATTERN
import com.example.storeapp.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.text.DecimalFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.absoluteValue

val multiRoundedCorner16 = MultiTransformation(
    CenterCrop(),
    RoundedCornersTransformation(
        16,
        0,
        RoundedCornersTransformation.CornerType.ALL
    )
)

val multiRoundedCorner24 = MultiTransformation(
    CenterCrop(),
    RoundedCornersTransformation(
        24,
        0,
        RoundedCornersTransformation.CornerType.ALL
    )
)

fun getDrawableFromId(id: Int): Int {
    return when (id) {
        1 -> R.drawable.img_recomendation_1
        2 -> R.drawable.img_recomendation_2
        else -> R.drawable.img_recomendation_3
    }
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun toCurrencyFormat(number: Int): String {
    return DecimalFormat(CURRENCY_FORMAT_PATTERN).format(number)
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun calculateTimeDifference(startTimeString: String, endTimeString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    val startTime = LocalDateTime.parse(startTimeString, formatter)
    val endTime = LocalDateTime.parse(endTimeString, formatter)

    val duration = Duration.between(startTime, endTime)

    val hours = duration.toHours().toDouble()
    val minutes = (duration.toMinutes() - hours * 60) / 60

    val timeDifference = (hours + minutes).absoluteValue
    val formattedNumber = String.format("%.1f", timeDifference)

    return "${formattedNumber}ч. в пути"
}

fun extractTime(timeString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    val dateTime = LocalDateTime.parse(timeString, inputFormatter)

    val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")

    return dateTime.format(outputFormatter)
}

fun getCurrentDate(): String {
    val currentDate = LocalDate.now()

    val formatter = DateTimeFormatter.ofPattern("d MMM, E", Locale("ru"))

    return currentDate.format(formatter)
}

fun getSelectDateForFlights(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("d MMM, E", Locale("ru"))

    return date.format(formatter)
}

fun getSelectDateForAllTickets(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

    return date.format(formatter)
}