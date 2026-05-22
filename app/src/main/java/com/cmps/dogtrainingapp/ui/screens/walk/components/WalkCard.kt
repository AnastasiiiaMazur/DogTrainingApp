package com.cmps.dogtrainingapp.ui.screens.walk.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.utils.hideKeyboardOnTap

@Composable
fun WalkCard(
    dateText: String = "",
    timeText: String = "",
    durationText: String = "",
    noteText: String? = "",
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(White)
            .padding(horizontal = 15.dp, vertical = 7.dp)
    ) {
        Column {
            Row {
                Text(
                    text = "${dateText}, ${timeText}",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = MyFontFamily,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$durationText min",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = MyFontFamily,
                    fontSize = 18.sp
                )
            }

            if (noteText != null) {
                Text(
                    text = noteText,
                    fontWeight = FontWeight.Normal,
                    fontFamily = MyFontFamily,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 7.dp)
                )
            }


            Row(
                modifier = Modifier
                    .padding(bottom = 5.dp, top = 5.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.edit_button),
                    contentDescription = "Edit button",
                    tint = DarkGray,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable{ onEditClicked() }
                )

                Spacer(modifier = Modifier.width(7.dp))

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.delete_button),
                    contentDescription = "Delete button",
                    tint = DarkGray,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable{ onDeleteClicked() }
                )
            }
        }

    }
}