package com.example.ejercicio2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio2.ui.theme.Ejercicio2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AceeptationScreen(
                        text1 = "All tasks completed",
                        text2 = "Nice work!",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AceeptationScreen(text1: String, text2: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = text1,
            fontWeight = Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )

        Text(
            text = text2,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AcceptationScreenPreview() {
    Ejercicio2Theme {
        AceeptationScreen (text1 = "All tasks completed", text2 = "Nice work!", modifier = Modifier)
    }
}