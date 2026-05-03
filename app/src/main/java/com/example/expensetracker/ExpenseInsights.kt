package com.example.expensetracker

import java.util.*

fun isWeekend(timestamp: Long): Boolean {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp
    val day = calendar.get(Calendar.DAY_OF_WEEK)

    return day == Calendar.SATURDAY || day == Calendar.SUNDAY
}

fun getWeekendInsight(expenses: List<Expense>): String {

    var weekendTotal = 0.0
    var weekdayTotal = 0.0

    for (e in expenses) {
        if (isWeekend(e.date)) {
            weekendTotal += e.amount
        } else {
            weekdayTotal += e.amount
        }
    }

    return if (weekendTotal > weekdayTotal) {
        "⚠️ You spend more on WEEKENDS"
    } else {
        "📊 You spend more on WEEKDAYS"
    }
}

fun getTopCategory(expenses: List<Expense>): String {

    val map = mutableMapOf<String, Double>()

    for (e in expenses) {
        map[e.category] = map.getOrDefault(e.category, 0.0) + e.amount
    }

    val top = map.maxByOrNull { it.value }

    return "🔥 Highest spending: ${top?.key ?: "N/A"}"
}
