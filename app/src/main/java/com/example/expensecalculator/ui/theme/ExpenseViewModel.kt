package com.example.expensecalculator.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensecalculator.Expense
import com.example.expensecalculator.ExpenseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application){
    private val dao = ExpenseDatabase.getDatabase(application).expenseDao()
    val expenses: Flow<List<Expense>> = dao.getAllExpenses()

    fun addExpense(expense: Expense){
        viewModelScope.launch {
            dao.insertExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense){
        viewModelScope.launch {
            dao.deleteExpense(expense)
        }
    }

}