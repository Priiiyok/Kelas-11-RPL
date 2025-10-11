package com.example.listdemoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listdemoapp.ui.theme.ListDemoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListDemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyList()
                }
            }
        }
    }
}

@Composable
fun MyList() {
    val context = LocalContext.current
    val items = (1..20).map { "Item $it" }

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(items) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Clicked on $item", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListDemoAppTheme {
        MyList()
    }
}