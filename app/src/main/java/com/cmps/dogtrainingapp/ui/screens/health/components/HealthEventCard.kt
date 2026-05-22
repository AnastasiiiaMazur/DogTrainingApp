package com.cmps.dogtrainingapp.ui.screens.health.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.local.entity.HealthEventType
import com.cmps.dogtrainingapp.data.local.entity.RepeatInterval
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Red
import com.cmps.dogtrainingapp.ui.theme.White
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

enum class EventStatus {
    UPCOMING,
    OVERDUE,
    COMPLETED
}

@RequiresApi(Build.VERSION_CODES.O)
fun getEventStatus(event: HealthEventEntity): EventStatus {
    if (event.isCompleted) {
        return EventStatus.COMPLETED
    }

    val eventDateTime = LocalDateTime.of(event.date, event.time)

    return if (eventDateTime.isBefore(LocalDateTime.now())) {
        EventStatus.OVERDUE
    } else {
        EventStatus.UPCOMING
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthEventCard(
    event: HealthEventEntity,
    onEditClicked: () -> Unit,
    onCompleteClicked: () -> Unit
) {
    val status = getEventStatus(event)

    val statusText = when (status) {
        EventStatus.UPCOMING -> "Upcoming"
        EventStatus.OVERDUE -> "Overdue"
        EventStatus.COMPLETED -> "Completed"
    }

    val dateText = event.date.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )

    val timeText = event.time.format(
        DateTimeFormatter.ofPattern("HH:mm")
    )

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(White)
            .padding(horizontal = 15.dp, vertical = 7.dp)
    ) {
        Row {
            Text(
                text = "${event.title}, ",
                fontWeight = FontWeight.SemiBold,
                fontFamily = MyFontFamily,
                fontSize = 17.sp
            )

            Text(
                text = event.type.displayName,
                fontWeight = FontWeight.Normal,
                fontFamily = MyFontFamily,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.edit_button),
                contentDescription = "Edit button",
                tint = DarkGray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onEditClicked() }
            )
        }

        Row(
            modifier = Modifier.padding(top = 7.dp)
        ) {
            Text(
                text = "$dateText, $timeText",
                fontWeight = FontWeight.Normal,
                fontFamily = MyFontFamily,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(
                    id = if (status == EventStatus.COMPLETED)
                        R.drawable.checkbox
                    else
                        R.drawable.checkbox_empty
                ),
                contentDescription = "completion mark",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(25.dp)
                    .clickable { onCompleteClicked() }
            )
        }

        Row(
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusDot(status = status)

            Spacer(modifier = Modifier.width(7.dp))

            Text(
                text = statusText,
                fontWeight = FontWeight.SemiBold,
                fontFamily = MyFontFamily,
                fontSize = 13.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewHealthEventCard() {

    val sampleEvent = HealthEventEntity(
        id = 1L,
        title = "Vet for cat",
        notes = "Annual checkup",
        type = HealthEventType.VET_APPOINTMENT,
        date = LocalDate.now(),//.plusDays(2),
        time = LocalTime.of(14, 30),
        repeat = RepeatInterval.NEVER,
        petId = 1L,
        isCompleted = false
    )

    HealthEventCard(
        event = sampleEvent,
        onEditClicked = {},
        onCompleteClicked = {}
    )
}