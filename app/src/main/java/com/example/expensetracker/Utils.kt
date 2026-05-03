package com.example.expensetracker

import java.util.Calendar
import java.util.Date
import java.text.SimpleDateFormat

fun calculateWeekendInsight(expenses: List<Expense>): String {

    val weekendTotal = expenses.filter { expense ->
        val cal = Calendar.getInstance()
        cal.timeInMillis = expense.date
        val day = cal.get(Calendar.DAY_OF_WEEK)

        day == Calendar.SATURDAY || day == Calendar.SUNDAY
    }.sumOf { it.amount }

    return "Weekend spending: Rs ${"%.2f".format(weekendTotal)}"
}

fun calculateTopCategory(expenses: List<Expense>): String {

    if (expenses.isEmpty()) return "No Data"

    return expenses
        .groupBy { it.category }
        .maxByOrNull { it.value.sumOf { e -> e.amount } }
        ?.key ?: "No Data"
}


    fun getDailyExpenses(expenses: List<Expense>): Map<String, Double> {

        val format = SimpleDateFormat("EEE")

        return expenses
            .groupBy { format.format(Date(it.date)) }
            .mapValues { it.value.sumOf { e -> e.amount } }
    }