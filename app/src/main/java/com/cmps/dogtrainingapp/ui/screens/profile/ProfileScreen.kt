package com.cmps.dogtrainingapp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.Gender
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity
import com.cmps.dogtrainingapp.ui.components.BasicButton
import com.cmps.dogtrainingapp.ui.components.ProfileInfoField
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.LightGray1
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White


@Composable
fun ProfileRoute(viewModel: ProfileViewModel) {
    val state =viewModel.uiState

    ProfileScreen(
        state = state,
        onEditClicked = { viewModel.onEditClicked() },
        onSaveClicked = { viewModel.onSaveClicked() }
    )
}

@Composable
fun ProfileScreen(
    state: ProfileUiState,
    onEditClicked: () -> Unit,
    onSaveClicked: () -> Unit
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray1)
            .padding(start = 20.dp, top = 50.dp, end = 20.dp)
    ) {
        Text(
            text = "Pet Profile",
            color = Black,
            fontSize = 26.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
            contentDescription = "Back button",
            tint = DarkGray,
            modifier = Modifier
                .padding(top = 15.dp)
                .size(25.dp)
        )

        Row (
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(White)
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.dog_default),
                contentDescription = "",
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

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.edit_button),
                contentDescription = "Edit button",
                tint = DarkGray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        true,
                        onClick = { onEditClicked() })
            )
        }

        ProfileInfoField(
            value = state.currentPet?.name ?: "",
            placeholder = "Enter pet's name",
            enabled = state.isEditing
        )

        ProfileInfoField(
            value = state.currentPet?.breed ?: "",
            placeholder = "Enter pet's breed",
            enabled = state.isEditing
        )

        ProfileInfoField(
            value = state.currentPet?.dateOfBirth ?: "",
            placeholder = "Enter date of birth",
            enabled = state.isEditing
        )

        ProfileInfoField(
            value = state.currentPetWeight?.let { "${it.weightKg} kg" } ?: "",
            placeholder = "Enter pet's weight",
            enabled = state.isEditing
        )

        ProfileInfoField(
            value = state.currentPet?.gender?.displayName ?: "",
            placeholder = "Other",
            enabled = state.isEditing
        )

        if (state.isEditing) {
            BasicButton(
                buttonText = "Save",
                onClick = onSaveClicked
            )
        }

        Row (
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(White)
                .padding(top = 13.dp, bottom = 13.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.pet_paw),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(25.dp)
            )

            Text(
                text = "Switch Pet",
                color = Black,
                fontSize = 16.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow),
                contentDescription = "Show list button",
                tint = Black,
                modifier = Modifier.rotate(270f)
            )
        }
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

    val sampleWeight = WeightEntryEntity(
        id = 1,
        petId = 1,
        date = "01.01.2025",
        weightKg = 3.5f
    )

    ProfileScreen(
        state = ProfileUiState(
            currentPet = samplePet,
            currentPetWeight = sampleWeight,
            allPets = listOf(samplePet),
            isLoading = false,
            isEditing = false
        ),
        onEditClicked = {},
        onSaveClicked = {}
    )
}