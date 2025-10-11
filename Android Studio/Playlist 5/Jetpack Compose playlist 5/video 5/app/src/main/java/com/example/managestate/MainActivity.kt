package com.example.managestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.managestate.ui.theme.ManagestateTheme
import androidx.compose.material3.OutlinedTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManagestateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen(modifier: Modifier = Modifier) {
    var name by remember {
        mutableStateOf("")
    }
    var result by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = name, onValueChange = {
            name = it
        })
        Button(onClick = {
            result = name
        }) {
            Text(text = "Tampil")
        }
        Text(text = "Hello $result")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ManagestateTheme {
        MyScreen()
    }
}