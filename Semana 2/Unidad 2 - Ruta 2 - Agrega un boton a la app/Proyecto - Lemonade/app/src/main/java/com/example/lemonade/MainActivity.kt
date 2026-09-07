package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadePrincipal()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePrincipal(modifier: Modifier = Modifier) {
    var etapa by remember { mutableStateOf(1) }
    var toquesNecesarios by remember { mutableStateOf(0) }

    Column(modifier = modifier.fillMaxSize()) {

        // Barra superior (Título)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFE57F))
                .padding(top = 48.dp, bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Lemonade",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        val etapaImagen = when (etapa) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            4 -> R.drawable.lemon_restart
            else -> R.drawable.lemon_tree
        }

        val textoInstruccion = when (etapa) {
            1 -> R.string.lemon_tree_text
            2 -> R.string.lemon_squeeze_text
            3 -> R.string.lemon_drink_text
            4 -> R.string.lemon_restart_text
            else -> R.string.lemon_tree_text
        }

        val descripcionImagen = when (etapa) {
            1 -> R.string.lemon_tree_desc
            2 -> R.string.lemon_desc
            3 -> R.string.lemonade_desc
            4 -> R.string.empty_glass_desc
            else -> R.string.lemon_tree_desc
        }

        LemonTextAndImage(
            textoId = textoInstruccion,
            imagenId = etapaImagen,
            descripcionId = descripcionImagen,
            onImageClick = {
                when (etapa) {
                    1 -> {
                        etapa = 2
                        toquesNecesarios = (2..4).random()
                    }
                    2 -> {
                        toquesNecesarios--
                        if (toquesNecesarios == 0) etapa = 3
                    }
                    3 -> etapa = 4
                    4 -> etapa = 1
                }
            }
        )
    }
}
@Composable
fun LemonTextAndImage(
    textoId: Int,
    imagenId: Int,
    descripcionId: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(textoId),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(2.dp, Color(105, 205, 216)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2))
        ) {
            Image(
                painter = painterResource(imagenId),
                contentDescription = stringResource(descripcionId)
            )
        }
    }
}