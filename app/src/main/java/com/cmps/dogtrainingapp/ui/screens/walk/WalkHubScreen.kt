package com.cmps.dogtrainingapp.ui.screens.walk

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.WalkEventEntity
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.utils.hideKeyboardOnTap
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WalkHubRoute(viewModel: WalkViewModel) {
    val state = viewModel.uiState

    WalkHubScreen(
        state = state
    )
}

@Composable
fun WalkHubScreen(state: WalkUiState) {

    LazyColumn (
        modifier = Modifier
            .hideKeyboardOnTap()
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = 50.dp,
            bottom = 100.dp,
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
                        fontWeight = FontWeight.SemiBold
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
                        .clickable{ }
                ) {
                    Image(
                        painter = painterResource(R.drawable.calendar_icon),
                        contentDescription = "calendar icon",
                        modifier = Modifier
                            .padding(end = 7.dp)
                            .size(20.dp)
                    )

                    Text(
                        text = "Date",
                        fontFamily = MyFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(top = 7.dp, bottom = 7.dp),
                    thickness = 1.dp
                )


            }
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
        )
    )
}