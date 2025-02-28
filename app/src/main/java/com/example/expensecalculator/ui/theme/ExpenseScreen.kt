package com.example.expensecalculator.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensecalculator.Expense

@Composable
fun ExpenseScreen(expenseViewModel: ExpenseViewModel = viewModel()){
    var title by remember { mutableSetOf(TextFieldValue("")) }
    var amount by remember { mutableSetOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Expanse Tracker", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value=title, onValueChange = { title=it }, label = {Text("Title")})
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = amount, onValueChange = {amount= it},label = {Text("Amount")})

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (title.text.isNotEmpty() && amount.text.isNotEmty()){
                expenseViewModel.addExpense(Expense(title = title.text, amount = amount.text.toDuble(),data="Today"))
                title = TextFieldValue("")
                amount = TextFieldValue("")
            }
        }){
            Text("Add Expense")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text="Expense List", style = MaterialTheme.typography.headlineSmall)

        ExpenseList(expenseViewModel)

    }
}

@Composable
fun ExpenseList(expenseViewModel: ExpenseViewModel){
    val expense by expenseViewModel.expenses.collectAsState(initial = emptyList())

    LazyColum {
        item(expense) { expanse ->
            Card(
                modifier = Modifier.fillMaxSize().padding(8.dp),
            )

        }
    }

}