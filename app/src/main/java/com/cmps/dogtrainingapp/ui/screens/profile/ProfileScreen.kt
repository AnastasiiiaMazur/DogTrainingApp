package com.cmps.dogtrainingapp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.data.local.entity.Gender
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White


@Composable
fun ProfileRoute(viewModel: ProfileViewModel) {
    val state =viewModel.uiState

    ProfileScreen(
        state = state
    )
}

@Composable
fun ProfileScreen(state: ProfileUiState) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Text(
            text = "Pet Profile",
            color = Black,
            fontSize = 26.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 50.dp, start = 20.dp)
        )
        Row () { }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val samplePet = PetEntity(
        id = 1,
        name = "Rex",
        breed = "German Shepherd",
        dateOfBirth = "01/01/2023",
        gender = Gender.MALE,
        imageUri = "default_dog"
    )

    ProfileScreen(
        state = ProfileUiState(
            currentPet = samplePet,
            allPets = listOf(samplePet),
            isLoading = false
        )
    )
}