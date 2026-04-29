package com.example.expensetracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Calendar

// ─────────────────────────────────────────────────────────────
// ✅ TOP-LEVEL helper functions — outside HomeScreen completely
// ─────────────────────────────────────────────────────────────

fun calculateWeekendInsight(expenses: List<Expense>): String {
    val calendar = Calendar.getInstance()
    val weekendExpenses = expenses.filter {
        calendar.timeInMillis = it.date
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        day == Calendar.SATURDAY || day == Calendar.SUNDAY
    }
    val total = weekendExpenses.sumOf { it.amount }
    return if (total > 0) "Weekend spending: Rs ${"%.2f".format(total)}" else "No weekend expenses yet"
}

fun calculateTopCategory(expenses: List<Expense>): String {
    if (expenses.isEmpty()) return "None"
    return expenses
        .groupBy { it.category }
        .maxByOrNull { it.value.sumOf { e -> e.amount } }
        ?.key ?: "None"
}

@Composable
fun HomeScreen(navController: NavController, viewModel: ExpenseViewModel) {

    val expenses by viewModel.expenses.collectAsState()

    var title    by remember { mutableStateOf("") }
    var amount   by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("General") }
    var expanded by remember { mutableStateOf(false) }

    val categories = listOf("Food", "Travel", "Shopping", "Bills")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Expense Tracker 💰",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ─── CATEGORY DROPDOWN ────────────────────────────────────────
        Box {
            OutlinedTextField(
                value = category,
                onValueChange = {},
                label = { Text("Category") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                readOnly = true
            )
            Box(
                modifier= Modifier
                    .matchParentSize()
                    .clickable {expanded= true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { cat ->
                    DropdownMenuItem(
                        text = { Text(cat) },
                        onClick = {
                            category = cat
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ─── TITLE INPUT ──────────────────────────────────────────────
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ─── AMOUNT INPUT ─────────────────────────────────────────────
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ─── ADD BUTTON ───────────────────────────────────────────────
        Button(
            onClick = {
                if (title.isNotEmpty() && amount.isNotEmpty()) {
                    viewModel.addExpense(
                        Expense(
                            title = title,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            category = category,
                            date = System.currentTimeMillis()
                        )
                    )
                    title = ""
                    amount = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Expense")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ─── INSIGHTS ─────────────────────────────────────────────────
        // ✅ Top-level functions called freely here — no errors
        Text(text = calculateWeekendInsight(expenses))
        Text(text = "Top Category: ${calculateTopCategory(expenses)}")

        Spacer(modifier = Modifier.height(10.dp))

        // ─── STATS BUTTON ─────────────────────────────────────────────
        Button(
            onClick = { navController.navigate("stats") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Stats 📊")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ─── EXPENSE LIST ─────────────────────────────────────────────
        LazyColumn {
            items(expenses) { expense ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {

                        Text(
                            text = expense.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(text = "Rs ${expense.amount}")

                        Text(
                            text = expense.category,
                            style = MaterialTheme.typography.labelSmall
                        )

                        Button(
                            onClick = { viewModel.deleteExpense(expense) }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }

    } // ✅ Column closes here
}