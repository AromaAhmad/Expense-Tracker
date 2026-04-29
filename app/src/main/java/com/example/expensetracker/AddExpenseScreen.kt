package com.example.expensetracker

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddExpenseScreen() {

    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(text = "Add Expense 💰")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // yahan save logic aayega later
        }) {
            Text("Save Expense")
        }
    }
}