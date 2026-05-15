package com.cmps.dogtrainingapp.ui.screens.profile

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
import com.cmps.dogtrainingapp.ui.theme.Red
import com.cmps.dogtrainingapp.ui.theme.White
import java.io.File
import java.time.LocalDate
import java.util.Calendar
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import com.cmps.dogtrainingapp.utils.dismissKeyboard
import com.cmps.dogtrainingapp.utils.hideKeyboardOnTap


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileRoute(viewModel: ProfileViewModel) {
    val state =viewModel.uiState

    ProfileScreen(
        state = state,
        onEditClicked = { viewModel.onEditClicked() },
        onSaveClicked = { viewModel.onSaveClicked() },
        onNameChanged = { viewModel.onNameChanged(it) },
        onBreedChanged = { viewModel.onBreedChanged(it) },
        onWeightChanged = { viewModel.onWeightChanged(it) },
        onGenderChanged = { viewModel.onGenderChanged(it) },
        onDateOfBirthChanged = { viewModel.onDateOfBirthChanged(it) },
        onImageChanged = { viewModel.onImageChanged(it) },
        onPetChanged = { viewModel.onPetChanged(it) },
        onAddNewPetClicked = { viewModel.onAddNewPetClicked() }
    )
}

@Composable
fun ProfileScreen(
    state: ProfileUiState,
    onEditClicked: () -> Unit,
    onSaveClicked: () -> Unit,
    onNameChanged: (String) -> Unit,
    onBreedChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onGenderChanged: (Gender) -> Unit,
    onDateOfBirthChanged: (String) -> Unit,
    onImageChanged: (String) -> Unit,
    onPetChanged: (PetEntity) -> Unit,
    onAddNewPetClicked: () -> Unit
) {

    var genderExpanded by remember { mutableStateOf(false) }
    var petListExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    fun openCalendar(context: Context) {
        val today = Calendar.getInstance()

        DatePickerDialog(
            context,
            R.style.MyDatePickerTheme,
            { _, year, month, day ->
                val formatted = String.format(
                    java.util.Locale.getDefault(),
                    "%02d/%02d/%04d",
                    day, month+1, year)
                onDateOfBirthChanged(formatted)
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri

        try {
            uri ?: return@rememberLauncherForActivityResult
            state.currentPet?.imageUri?.let { oldUriStr ->
                val oldUri = oldUriStr.toUri()
                if (oldUri.scheme == "file") {
                    File(oldUri.path ?: "").takeIf { it.exists() }?.delete()
                }
            }

            val file = File(context.filesDir, "pet_image_${System.currentTimeMillis()}.jpg")

            context.contentResolver.openInputStream(uri)?.use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            onImageChanged(file.absolutePath)
        } catch (e: Exception) {
            Log.e("Error", "Failed copying image", e)
        }
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray1)
            .padding(start = 20.dp, top = 50.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
            .hideKeyboardOnTap()
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

            val imageModel: Any = when {
                state.isEditing && selectedImageUri != null -> {
                    selectedImageUri!!
                }

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
                    .clickable {
                        if (state.isEditing) {
                            launcher.launch("image/*")
                        }
                    }
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

            if (!state.isEditing) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.edit_button),
                    contentDescription = "Edit button",
                    tint = DarkGray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(onClick = { onEditClicked() })
                )
            }
        }

        if (state.isEditing) {
            Text(
                text = "* press on image to upload a new one",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                color = Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = MyFontFamily
            )
        }

        ProfileInfoField(
            value =
                if (state.isEditing) state.editedName
                else state.currentPet?.name ?: "",
            placeholder = "Enter pet's name",
            enabled = state.isEditing,
            onValueChange = onNameChanged
        )

        ProfileInfoField(
            value =
                if (state.isEditing) state.editedBreed
                else state.currentPet?.breed ?: "Enter pet's breed",
            placeholder = "Enter pet's breed",
            enabled = state.isEditing,
            onValueChange = onBreedChanged
        )

        Text(
            text =
                if (state.isEditing) state.editedDateOfBirth ?: ""
                else state.currentPet?.dateOfBirth ?: "Enter date of birth",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .border(
                    shape = RoundedCornerShape(10.dp),
                    width = 1.dp,
                    color = if (state.isEditing) Black else LightGray
                )
                .padding(top = 16.dp, bottom = 16.dp, start = 12.dp)
                .clickable { if (state.isEditing) openCalendar(context) },
            color = if (state.isEditing) Black else DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = MyFontFamily
        )

        ProfileInfoField(
            value =
                if (state.isEditing) state.editedWeight
                else state.currentPetWeight?.let { "${it.weightKg} kg" } ?: "",
            placeholder = "Enter pet's weight",
            enabled = state.isEditing,
            onValueChange = onWeightChanged
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text =
                    if (state.isEditing) state.editedGender.displayName
                    else state.currentPet?.gender?.displayName ?: "Other",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .border(
                        shape = RoundedCornerShape(10.dp),
                        width = 1.dp,
                        color = if (state.isEditing) Black else LightGray
                    )
                    .padding(top = 16.dp, bottom = 16.dp, start = 12.dp)
                    .clickable {
                        if (state.isEditing) {
                            genderExpanded = true
                        }
                    },
                color = if (state.isEditing) Black else DarkGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = MyFontFamily
            )

            DropdownMenu(
                expanded = genderExpanded,
                onDismissRequest = { genderExpanded = false },
                modifier = Modifier
                    .background(White)
                    .clip(RoundedCornerShape(10.dp))
            ) {

                Gender.entries.forEach { gender ->
                    DropdownMenuItem(
                        text = { Text(
                            text = gender.displayName,
                            fontSize = 16.sp,
                            color = Black,
                            fontFamily = MyFontFamily,
                            fontWeight = FontWeight.Normal) },
                        onClick = {
                            onGenderChanged(gender)
                            genderExpanded = false
                        }
                    )
                }
            }
        }

        if (state.errorMessage != null) {
            Text(
                text = state.errorMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                color = Red,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = MyFontFamily
            )
        }

        if (state.isEditing) {
            BasicButton(
                buttonText = "Save",
                onClick = {
                    dismissKeyboard(focusManager, keyboardController)
                    onSaveClicked() }
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

            Box {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow),
                    contentDescription = "Show list button",
                    tint = Black,
                    modifier = Modifier
                        .rotate(
                            if (petListExpanded) 90f else 270f
                        )
                        .clickable { petListExpanded = true }
                )

                DropdownMenu(
                    expanded = petListExpanded,
                    onDismissRequest = { petListExpanded = false },
                    modifier = Modifier
                        .background(White)
                        .clip(RoundedCornerShape(10.dp))
                ) {

                    state.allPets.forEach { pet ->
                        DropdownMenuItem(
                            text = { Text(
                                text = pet.name,
                                fontSize = 16.sp,
                                color = Black,
                                fontFamily = MyFontFamily,
                                fontWeight = FontWeight.Normal) },
                            leadingIcon = {
                                val petImageModel: Any = if (
                                    !pet.imageUri.isNullOrBlank() &&
                                    File(pet.imageUri).exists()
                                ) {
                                    Uri.fromFile(File(pet.imageUri))
                                } else {
                                    R.drawable.dog_default
                                }

                                AsyncImage(
                                    model = petImageModel,
                                    contentDescription = "Pet image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .size(35.dp)
                                        .clip(CircleShape)
                                )
                            },
                            onClick = {
                                onPetChanged(pet)
                                petListExpanded = false
                            }
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.padding(2.dp))
                    }

                    DropdownMenuItem(
                        text = { Text(
                            text = "+ Add a new pet",
                            fontSize = 16.sp,
                            color = Black,
                            fontFamily = MyFontFamily,
                            fontWeight = FontWeight.Normal) },
                        onClick = {
                            onAddNewPetClicked()
                            petListExpanded = false
                        }
                    )
                }
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
        date = LocalDate.of(2025, 4, 17),
        weightKg = 3.5f
    )

    ProfileScreen(
        state = ProfileUiState(
            currentPet = samplePet,
            currentPetWeight = sampleWeight,
            allPets = listOf(samplePet),
            isLoading = false,
            isEditing = false,
            editedName = "",
            editedBreed = "",
            editedDateOfBirth = "",
            editedWeight = "",
            editedGender = Gender.MALE
        ),
        onEditClicked = {},
        onSaveClicked = {},
        onNameChanged = {},
        onBreedChanged = {},
        onWeightChanged = {},
        onGenderChanged = {},
        onDateOfBirthChanged = {},
        onImageChanged = {},
        onPetChanged = {},
        onAddNewPetClicked = {}
    )
}