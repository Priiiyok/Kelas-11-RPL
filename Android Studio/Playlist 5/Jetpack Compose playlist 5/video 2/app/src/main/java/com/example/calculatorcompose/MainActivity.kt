package com.example.calculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorScreen()
        }
    }
}

@Composable
fun CalculatorScreen() {
    var display by remember { mutableStateOf("0") }
    var operand1 by remember { mutableStateOf<Double?>(null) }
    var pendingOp by remember { mutableStateOf<String?>(null) }

    fun onNumberClick(num: String) {
        display = if (display == "0") num else display + num
    }

    fun onOperatorClick(op: String) {
        operand1 = display.toDoubleOrNull()
        pendingOp = op
        display = "0"
    }

    fun onEquals() {
        val op1 = operand1
        val op2 = display.toDoubleOrNull()
        if (op1 != null && op2 != null && pendingOp != null) {
            val result = when (pendingOp) {
                "+" -> op1 + op2
                "-" -> op1 - op2
                "×" -> op1 * op2
                "÷" -> if (op2 != 0.0) op1 / op2 else "Error".toDoubleOrNull()
                else -> op2
            }
            display = result?.toString() ?: "Error"
            // reset
            operand1 = null
            pendingOp = null
        }
    }

    fun onClear() {
        display = "0"
        operand1 = null
        pendingOp = null
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Display
        Text(
            text = display,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            maxLines = 1,
            color = Color.Black,
        )

        // Buttons
        val buttonModifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
            .padding(4.dp)

        // Rows of buttons
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.weight(1f)) {
                CalculatorButton("7", buttonModifier) { onNumberClick("7") }
                CalculatorButton("8", buttonModifier) { onNumberClick("8") }
                CalculatorButton("9", buttonModifier) { onNumberClick("9") }
                CalculatorButton("÷", buttonModifier) { onOperatorClick("÷") }
            }
            Row(modifier = Modifier.weight(1f)) {
                CalculatorButton("4", buttonModifier) { onNumberClick("4") }
                CalculatorButton("5", buttonModifier) { onNumberClick("5") }
                CalculatorButton("6", buttonModifier) { onNumberClick("6") }
                CalculatorButton("×", buttonModifier) { onOperatorClick("×") }
            }
            Row(modifier = Modifier.weight(1f)) {
                CalculatorButton("1", buttonModifier) { onNumberClick("1") }
                CalculatorButton("2", buttonModifier) { onNumberClick("2") }
                CalculatorButton("3", buttonModifier) { onNumberClick("3") }
                CalculatorButton("-", buttonModifier) { onOperatorClick("-") }
            }
            Row(modifier = Modifier.weight(1f)) {
                CalculatorButton("0", buttonModifier.weight(2f)) { onNumberClick("0") }
                CalculatorButton(".", buttonModifier) {
                    if (!display.contains(".")) onNumberClick(".")
                }
                CalculatorButton("+", buttonModifier) { onOperatorClick("+") }
            }
            Row(modifier = Modifier.weight(1f)) {
                CalculatorButton("C", buttonModifier.weight(3f)) { onClear() }
                CalculatorButton("=", buttonModifier.weight(1f)) { onEquals() }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (label in listOf("C", "÷", "×", "-", "+", "=")) Color(0xFFFF9800)
            else Color(0xFFEEEEEE),
            contentColor = if (label in listOf("C", "÷", "×", "-", "+", "=")) Color.White else Color.Black
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = label, fontSize = 24.sp, fontWeight = FontWeight.Medium)
    }
}
