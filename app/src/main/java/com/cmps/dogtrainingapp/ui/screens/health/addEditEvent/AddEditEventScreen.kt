package com.cmps.dogtrainingapp.ui.screens.health.addEditEvent

import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.HealthEventType
import com.cmps.dogtrainingapp.data.local.entity.RepeatInterval
import com.cmps.dogtrainingapp.ui.components.BasicButton
import com.cmps.dogtrainingapp.ui.components.openDatePicker
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Red
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.utils.dismissKeyboard
import com.cmps.dogtrainingapp.utils.hideKeyboardOnTap
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditEventRoute(
    eventId: Long?,
    navController: NavHostController,
    viewModel: AddEditViewModel
) {

    val state = viewModel.uiState

    AddEditEventScreen(
        navController = navController,
        eventId = eventId,
        state = state,
        onDateChanged = { viewModel.onDateChanged(it) },
        onTimeChanged = { viewModel.onTimeChanged(it) },
        onTitleChanged = { viewModel.onTitleChanged(it) },
        onNoteChanged = { viewModel.onNoteChanged(it) },
        onTypeChanged = viewModel::onTypeChanged,
        onRepeatChanged = viewModel::onRepeatChanged,
        onSaveClicked = viewModel::onSaveClicked,
        onDeleteClicked = viewModel::onDeleteClicked
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditEventScreen(
    navController: NavHostController,
    eventId: Long?,
    state: AddEditUiState,
    onDateChanged: (LocalDate) -> Unit,
    onTimeChanged: (LocalTime) -> Unit,
    onTitleChanged: (String) -> Unit,
    onNoteChanged: (String) -> Unit,
    onTypeChanged: (HealthEventType) -> Unit,
    onRepeatChanged: (RepeatInterval) -> Unit,
    onSaveClicked: () -> Unit,
    onDeleteClicked: (Long) -> Unit
) {

    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var typeExpanded by remember { mutableStateOf(false) }
    var repeatExpanded by remember { mutableStateOf(false) }

    fun openTimePicker(context: Context) {

        val now = Calendar.getInstance()

        TimePickerDialog(
            context,
            { _, hour, minute ->

                onTimeChanged( LocalTime.of(hour, minute) )
            },
            now.get(Calendar.HOUR_OF_DAY),
            now.get(Calendar.MINUTE),
            true
        ).show()
    }

    Column(
        modifier = Modifier
            .hideKeyboardOnTap()
            .fillMaxSize()
            .padding(
                top = 50.dp,
                bottom = 30.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Text(
            text = if (eventId == null) {
                "Add Health Event"
            } else {
                "Edit Health Event"
            },
            color = Black,
            fontSize = 26.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                contentDescription = "Back button",
                tint = DarkGray,
                modifier = Modifier
                    .size(25.dp)
                    .clickable { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.weight(1f))

            if (eventId != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.delete_button),
                    contentDescription = "Delete button",
                    tint = Red,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .size(18.dp)
                        .align(Alignment.CenterVertically)
                )

                Text(
                    text = "Delete Event",
                    fontFamily = MyFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Red,
                    modifier = Modifier
                        .padding(top = 7.dp)
                        .clickable{
                            dismissKeyboard(focusManager, keyboardController)
                            eventId?.let { id ->
                                onDeleteClicked(id)
                            }
                        }
                        .align(Alignment.CenterVertically)
                )
            }
        }

        Column {
            Column(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                BasicTextField(
                    value = state.selectedTitle,
                    onValueChange = {
                        if (it.length <= 100) {
                            onTitleChanged(it)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 5.dp),
                    textStyle = TextStyle(
                        color = Black,
                        fontSize = 15.sp,
                        fontFamily = MyFontFamily,
                        fontWeight = FontWeight.SemiBold
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->

                    if (state.selectedTitle.isEmpty()) {
                        Text(
                            text = "Event Title",
                            color = Gray,
                            fontSize = 15.sp,
                            fontFamily = MyFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                        innerTextField()
                    }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = 7.dp, bottom = 7.dp),
                    thickness = 1.dp
                )

                BasicTextField(
                    value = state.selectedNotes,
                    onValueChange = {
                        if ( it.length <= 200 ) {
                            onNoteChanged(it)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 5.dp),
                    textStyle = TextStyle(
                        color = Black,
                        fontSize = 15.sp,
                        fontFamily = MyFontFamily
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->

                    if (state.selectedNotes.isEmpty()) {
                        Text(
                            text = "Note",
                            color = Gray,
                            fontSize = 15.sp,
                            fontFamily = MyFontFamily
                        )
                    }

                        innerTextField()
                    }
                )

            if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage,
                    fontFamily = MyFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Red,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            }

            Spacer(modifier = Modifier.height(7.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(White)
                        .fillMaxWidth()
                        .clickable { typeExpanded = true }
                        .padding(vertical = 10.dp, horizontal = 10.dp)
                ) {
                    Text(
                        text = state.selectedType.displayName ?: "Event Type",
                        fontFamily = MyFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(20.dp)
                            .rotate(270f)
                    )
                }

                DropdownMenu(
                    expanded = typeExpanded,
                    onDismissRequest = { typeExpanded = false },
                    modifier = Modifier
                        .background(White)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    HealthEventType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(
                                text = type.displayName,
                                fontSize = 16.sp,
                                color = Black,
                                fontFamily = MyFontFamily,
                                fontWeight = FontWeight.Normal) },
                            onClick = {
                                onTypeChanged(type)
                                typeExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(7.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .fillMaxWidth()
                    .clickable {
                        openDatePicker(
                            context = context,
                            onDateSelected = onDateChanged
                        )
                    }
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.calendar_icon),
                    contentDescription = "calendar icon",
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(20.dp)
                )

                Text(
                    text = //"Date",
                        state.selectedDate.format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy") ),
                    fontFamily = MyFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(7.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .fillMaxWidth()
                    .clickable { openTimePicker(context) }
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.time_icon),
                    contentDescription = "time icon",
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(20.dp)
                )

                Text(
                    text = //"Time",
                        state.selectedTime.format(
                        DateTimeFormatter.ofPattern("HH:mm") ),
                    fontFamily = MyFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(7.dp))


            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(White)
                        .fillMaxWidth()
                        .clickable { repeatExpanded = true }
                        .padding(vertical = 10.dp, horizontal = 10.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.time_icon),
                        contentDescription = "time icon 2",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(20.dp)
                    )

                    Text(
                        text = state.selectedInterval.displayName ?: "Repeat",
                        fontFamily = MyFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = "calendar icon",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(20.dp)
                            .rotate(270f)
                    )
                }

                DropdownMenu(
                    expanded = repeatExpanded,
                    onDismissRequest = { repeatExpanded = false },
                    modifier = Modifier
                        .background(White)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    RepeatInterval.entries.forEach { repeat ->
                        DropdownMenuItem(
                            text = { Text(
                                text = repeat.displayName,
                                fontSize = 16.sp,
                                color = Black,
                                fontFamily = MyFontFamily,
                                fontWeight = FontWeight.Normal) },
                            onClick = {
                                onRepeatChanged(repeat)
                                repeatExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        BasicButton(
            buttonText = if (eventId == null) "Add Event" else "Save Changes",
            onClick = {
                onSaveClicked()

                Toast.makeText(
                    context,
                    if (eventId != null) "Health event updated!" else "Health event saved!",
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        Text(
            text = "Cancel",
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Red,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable{
                    dismissKeyboard(focusManager, keyboardController)
                    navController.popBackStack()
                }
                .align(Alignment.CenterHorizontally)
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewScreen() {
    AddEditEventScreen(
        navController = rememberNavController(),
        eventId = null,
        state = AddEditUiState(
            errorMessage = "vjnpn"
        ),
        onTimeChanged = {},
        onDateChanged = {},
        onTitleChanged = {},
        onNoteChanged = {},
        onTypeChanged = {},
        onRepeatChanged ={},
        onSaveClicked = {},
        onDeleteClicked = {}
    )
}
