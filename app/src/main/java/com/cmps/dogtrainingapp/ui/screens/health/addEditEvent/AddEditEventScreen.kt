package com.cmps.dogtrainingapp.ui.screens.health.addEditEvent

import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.cmps.dogtrainingapp.ui.components.openDatePicker
import com.cmps.dogtrainingapp.ui.navigation.navigateHome
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Orange
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.utils.dismissKeyboard
import com.cmps.dogtrainingapp.utils.hideKeyboardOnTap
import java.util.Calendar


@Composable
fun AddEditEventRoute(
    eventId: Long?,
    navController: NavHostController
) {

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditEventScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    fun openTimePicker(context: Context) {

        val now = Calendar.getInstance()

        TimePickerDialog(
            context,
            { _, hour, minute ->

                //onTimeChanged( LocalTime.of(hour, minute) )
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
                bottom = 50.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
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
                .clickable { navController.navigateHome() }
        )

        Column(
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
                    .clickable { openTimePicker(context) }
            ) {
                Image(
                    painter = painterResource(R.drawable.time_icon),
                    contentDescription = "time icon",
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(20.dp)
                )

                Text(
                    text = "-",
//                        state.selectedTime.format(
//                        DateTimeFormatter.ofPattern("HH:mm") ),
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
                    .clickable {
                        openDatePicker(
                            context = context,
                            onDateSelected = {}//onDateChanged
                        )
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.calendar_icon),
                    contentDescription = "calendar icon",
                    modifier = Modifier
                        .padding(end = 7.dp)
                        .size(20.dp)
                )

                Text(
                    text = "",
//                        state.selectedDate.format(
//                        DateTimeFormatter.ofPattern("dd/MM/yyyy") ),
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
                value = "",//state.notes,
                onValueChange = {
                    if (it.length <= 100) {
                        //onNotesChanged(it)
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

//                    if (state.notes.isEmpty()) {
//                        Text(
//                            text = "Note",
//                            color = Gray,
//                            fontSize = 15.sp,
//                            fontFamily = MyFontFamily
//                        )
//                    }

                    innerTextField()
                }
            )

            HorizontalDivider(
                modifier = Modifier.padding(top = 7.dp, bottom = 7.dp),
                thickness = 1.dp
            )

            BasicTextField(
                value = "",//state.durationText,
                onValueChange = {
                    if (
                        it.length <= 3 &&
                        it.all { char -> char.isDigit() }
                    ) {
                        //onDurationChanged(it)
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

//                    if (state.durationText.isEmpty()) {
//                        Text(
//                            text = "Duration (min)",
//                            color = Gray,
//                            fontSize = 15.sp,
//                            fontFamily = MyFontFamily
//                        )
//                    }

                    innerTextField()
                }
            )

//            if (state.errorMessage != null) {
//                Text(
//                    text = state.errorMessage,
//                    fontFamily = MyFontFamily,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 14.sp,
//                    color = Red,
//                    modifier = Modifier.padding(horizontal = 8.dp)
//                )
//            }

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
                        .clickable {
                            dismissKeyboard(focusManager, keyboardController)
                        }
                    //onSaveClicked() }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewScreen() {
    AddEditEventScreen(
        navController = rememberNavController()
    )
}
