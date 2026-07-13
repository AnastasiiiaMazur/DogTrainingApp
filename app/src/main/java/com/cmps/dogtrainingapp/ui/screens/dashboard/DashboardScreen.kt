package com.cmps.dogtrainingapp.ui.screens.dashboard

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White
import java.io.File


@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel,
    navController:NavHostController
) {

    val state = viewModel.uiState

    DashboardScreen(
        state = state
    )
}

@Composable
fun DashboardScreen(
    state: DashboardUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 50.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row (
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(White)
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val imageModel: Any = when {
                !state.currentPet?.imageUri.isNullOrBlank() &&
                        File(state.currentPet?.imageUri!!).exists() -> {
                    Uri.fromFile(File(state.currentPet!!.imageUri!!))
                }

                else -> {
                    R.drawable.dog_default
                }
            }

            AsyncImage(
                model = imageModel,
                contentDescription = "Pet image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(45.dp)
                    .clip(CircleShape)
            )

            Text(
                text = state.currentPet?.name ?: "Your Pet",
                color = Black,
                fontSize = 22.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Composable
@Preview
fun Preview() {
    DashboardScreen(
        state = DashboardUiState(

        )
    )
}