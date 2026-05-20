package com.cmps.dogtrainingapp.ui.screens.walk

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.WalkEventEntity
import com.cmps.dogtrainingapp.ui.screens.walk.components.WalkCard
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Orange
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.utils.hideKeyboardOnTap
import java.time.LocalDate
import java.time.LocalTime
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import java.time.format.DateTimeFormatter
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WalkHubRoute(viewModel: WalkViewModel) {
    val state = viewModel.uiState

    WalkHubScreen(
        state = state,
        onSaveClicked = { viewModel.onSaveClicked() },
        onNotesChanged = { viewModel.onNotesChanged(it) },
        onDurationChanged = { viewModel.onDurationChanged(it) },
        onDateChanged = { viewModel.onDateChanged(it) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WalkHubScreen(
    state: WalkUiState,
    onSaveClicked: () -> Unit,
    onNotesChanged: (String) -> Unit,
    onDurationChanged: (String) -> Unit,
    onDateChanged: (LocalDate) -> Unit
) {

    val context = LocalContext.current

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
                onDateChanged(LocalDate.of(year, month + 1, day))
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

    LazyColumn (
        modifier = Modifier
            .hideKeyboardOnTap()
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = 50.dp,
            bottom = 50.dp,
            start = 20.dp,
            end = 20.dp
        )
    ) {
        item {
            Text(
                text = "Walk Hub",
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

            Column (
                modifier = Modifier
                    .padding(top = 15.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(White)
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable{ }
                ) {
                    Image(
                        painter = painterResource(R.drawable.time_icon),
                        contentDescription = "time icon",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(20.dp)
                    )

                    Text(
                        text = "Time",
                        fontFamily = MyFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 7.dp, bottom = 7.dp),
                    thickness = 1.dp
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable{ openCalendar(context) }
                ) {
                    Image(
                        painter = painterResource(R.drawable.calendar_icon),
                        contentDescription = "calendar icon",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(20.dp)
                    )

                    Text(
                        text =  state.selectedDate.format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy") ),
                        fontFamily = MyFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(top = 7.dp, bottom = 7.dp),
                    thickness = 1.dp
                )

                BasicTextField(
                    value = state.notes,
                    onValueChange = {
                        if (it.length <= 100) {
                            onNotesChanged(it)
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

                        if (state.notes.isEmpty()) {
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

                HorizontalDivider(
                    modifier = Modifier.padding(top = 7.dp, bottom = 7.dp),
                    thickness = 1.dp
                )

                BasicTextField(
                    value = state.durationText,
                    onValueChange = {
                        if (
                            it.length <= 3 &&
                            it.all { char -> char.isDigit() }
                        ) {
                            onDurationChanged(it)
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

                        if (state.durationText.isEmpty()) {
                            Text(
                                text = "Duration (min)",
                                color = Gray,
                                fontSize = 15.sp,
                                fontFamily = MyFontFamily
                            )
                        }

                        innerTextField()
                    }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = 7.dp, bottom = 7.dp),
                    thickness = 1.dp
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Save",
                        fontFamily = MyFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Orange,
                        modifier = Modifier
                            .clickable{ onSaveClicked() }
                    )
                }
            }
        }

        items(state.walks) { walk ->
            WalkCard(
                dateText = walk.date.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy") ),
                timeText = walk.time.format(
                        DateTimeFormatter.ofPattern("HH:mm") ),
                durationText = walk.durationMinutes.toString(),
                noteText = walk.notes
            )
        }

        item {
            Text(
                text = "Weekly Summary",
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = Black,
                modifier = Modifier.padding(top = 15.dp)
            )

            Text(
                text = "Total walks: ${state.totalWalks}",
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Black,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = "Average duration: ${state.averageDuration} min",
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Black,
                modifier = Modifier.padding(top = 5.dp)
            )

            Text(
                text = "Total time walked: ${state.totalWalkTime} min",
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Black,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewScreen() {

    val sampleWalks = listOf(
        WalkEventEntity(
            id = 1,
            date = LocalDate.now(),
            time = LocalTime.of(14, 30),
            durationMinutes = 45,
            notes = "Evening walk",
            petId = 1
        ),
        WalkEventEntity(
            id = 2,
            date = LocalDate.now().minusDays(1),
            time = LocalTime.of(10, 15),
            durationMinutes = 30,
            notes = "Morning park walk",
            petId = 1
        )
    )

    WalkHubScreen(
        state = WalkUiState(
            walks = sampleWalks,
            selectedDate = LocalDate.now(),
            selectedTime = LocalTime.now(),
            durationText = "45",
            notes = "Walk around the park",

            totalWalks = 2,
            averageDuration = 37.5f,
            totalWalkTime = 75
        ),
        onSaveClicked = {},
        onNotesChanged = {},
        onDurationChanged = {},
        onDateChanged = {}
    )
}