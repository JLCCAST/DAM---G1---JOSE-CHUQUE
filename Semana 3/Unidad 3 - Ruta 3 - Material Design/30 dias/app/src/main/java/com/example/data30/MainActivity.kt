package com.example.data30

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.data30.data.DataSource
import com.example.data30.model.DayTip
import com.example.data30.ui.theme.Data30Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Data30Theme {

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Data30Screen()
                }
            }
        }
    }
}

@Composable
fun Data30Screen() {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            Header()
        }

        items(DataSource.days) { dayTip ->
            DayCard(dayTip)
        }
    }
}

@Composable
fun DayCard(dayTip: DayTip) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "DÍA ${dayTip.day}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = stringResource(dayTip.titleRes),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Image(
                painter = painterResource(
                    id = dayTip.imageRes
                ),
                contentDescription = stringResource(dayTip.titleRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = stringResource(dayTip.descriptionRes),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun Header() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Text(
            text = "DATA30",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "30 días para mejorar tus habilidades en datos",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun Data30Preview() {
    Data30Theme {
        Data30Screen()
    }
}