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

@Composable
fun HomeScreen(navController: NavController, viewModel: ExpenseViewModel) {

    val expenses by viewModel.expenses.collectAsState()

    val totalAmount = expenses.sumOf { it.amount }

    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
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

        // ─── CATEGORY DROPDOWN ─────────────────────────────
        Box {

            OutlinedTextField(
                value = category,
                onValueChange = {},
                label = { Text("Category") },
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true
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

            // click layer
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = true }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ─── TITLE ─────────────────────────────
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ─── AMOUNT ────────────────────────────
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ─── ADD BUTTON ────────────────────────
        Button(
            onClick = {
                if (title.isNotBlank() && amount.isNotBlank()) {
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

        // ─── INSIGHTS ──────────────────────────
        Text(text = calculateWeekendInsight(expenses))
        Text(text = "Top Category: ${calculateTopCategory(expenses)}")

        Text(
            text = "Total Spending: Rs $totalAmount",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        // ─── NAVIGATION ────────────────────────
        Button(
            onClick = { navController.navigate("stats") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Stats 📊")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ─── LIST ──────────────────────────────
        LazyColumn {
            items(expenses) { expense ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {

                        Text(expense.title)
                        Text("Rs ${expense.amount}")
                        Text(expense.category)

                        Button(
                            onClick = { viewModel.deleteExpense(expense) }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}