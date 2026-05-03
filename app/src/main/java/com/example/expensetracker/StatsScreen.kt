package com.example.expensetracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ✅ Top-level composable
@Composable
fun StatsScreen(viewModel: ExpenseViewModel) {

    val expenses by viewModel.expenses.collectAsState()

    val dailyData = remember(expenses) {
        getDailyExpenses(expenses)
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Daily Spending 📊")

        Spacer(modifier = Modifier.height(16.dp))

        if (dailyData.isEmpty()) {
            Text("No data available")
        } else {
            dailyData.forEach { (day, amount) ->

                Column {

                    Text(day)

                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width((amount / 50).coerceIn(1.0, 300.0).dp)  // clamp between 1dp and 300dp
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    Text("Rs $amount")

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}