package com.osmanacikgoz.stationsapp.base

import java.text.SimpleDateFormat

fun String.dateConverter(): String {
    val testFormat = SimpleDateFormat("yyyy-MM-dd")
    val formatDate = testFormat.parse(this)
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
    return simpleDateFormat.format(formatDate)
}