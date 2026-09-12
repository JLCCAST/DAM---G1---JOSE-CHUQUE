package com.example.Cursos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Cursos.modelo.Topic
import com.example.Cursos.ui.theme.CursosTheme
import com.example.Cursos.Data.DataSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopicList(
                        list = DataSource.topics,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TopicList(list: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(list) { topic ->
            TopicThing(topic = topic)
        }
    }
}

@Composable
fun TopicThing(topic: Topic, modifier: Modifier = Modifier) {

    val nombreTexto = stringResource(id = topic.nombre)
    val imagenCurso = painterResource(id = topic.imagen)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color(0xFFEFE8F4))
    ) {
        Image(
            painter = imagenCurso,
            contentDescription = nombreTexto,
            modifier = Modifier.size(68.dp),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(
                text = nombreTexto,
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
            )

            Row(
                modifier = Modifier.padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = topic.cupos.toString(),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}