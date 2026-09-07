package com.example.artspace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var currentArtwork by remember { mutableIntStateOf(1) }

    val imageResource = when (currentArtwork) {
        1 -> R.drawable.obra_1
        2 -> R.drawable.obra_2
        else -> R.drawable.obra_3
    }
    val titleResource = when (currentArtwork) {
        1 -> R.string.artwork_title
        2 -> R.string.artwork_title_2
        else -> R.string.artwork_title_3
    }
    val artistResource = when (currentArtwork) {
        1 -> R.string.artwork_artist
        2 -> R.string.artwork_artist_2
        else -> R.string.artwork_artist_3
    }
    val yearResource = when (currentArtwork) {
        1 -> R.string.artwork_year
        2 -> R.string.artwork_year_2
        else -> R.string.artwork_year_3
    }

    val onPrevious = {
        currentArtwork = when (currentArtwork) {
            1 -> 3
            2 -> 1
            else -> 2
        }
    }
    val onNext = {
        currentArtwork = when (currentArtwork) {
            1 -> 2
            2 -> 3
            else -> 1
        }
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .safeDrawingPadding()
                .padding(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtworkWall(
                imageRes = imageResource,
                titleRes = titleResource,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ArtworkDescriptor(
                    titleRes = titleResource,
                    artistRes = artistResource,
                    yearRes = yearResource
                )
                Spacer(modifier = Modifier.height(28.dp))
                DisplayController(
                    onPreviousClick = onPrevious,
                    onNextClick = onNext
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ArtworkWall(
                imageRes = imageResource,
                titleRes = titleResource,
                modifier = Modifier.weight(1f)
            )
            ArtworkDescriptor(
                titleRes = titleResource,
                artistRes = artistResource,
                yearRes = yearResource
            )
            Spacer(modifier = Modifier.height(28.dp))
            DisplayController(
                onPreviousClick = onPrevious,
                onNextClick = onNext
            )
        }
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes imageRes: Int,
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .border(width = 3.dp, color = Color.Gray),
        shadowElevation = 8.dp
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = titleRes),
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    @StringRes titleRes: Int,
    @StringRes artistRes: Int,
    @StringRes yearRes: Int) {
    Surface(
        color = Color(0xFFECEFF1),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = titleRes),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(id = artistRes),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(${stringResource(id = yearRes)})",
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(id = R.string.button_previous))
        }
        Spacer(modifier = Modifier.width(32.dp))
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(id = R.string.button_next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}