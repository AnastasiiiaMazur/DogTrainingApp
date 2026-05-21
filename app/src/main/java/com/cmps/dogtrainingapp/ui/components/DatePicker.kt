package com.cmps.dogtrainingapp.ui.components

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.cmps.dogtrainingapp.R
import java.time.LocalDate
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
fun openDatePicker(
    context: Context,
    onDateSelected: (LocalDate) -> Unit
) {

    val today = Calendar.getInstance()

    DatePickerDialog(
        context,
        R.style.MyDatePickerTheme,

        { _, year, month, day ->

            onDateSelected(
                LocalDate.of(
                    year,
                    month + 1,
                    day
                )
            )
        },

        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH),
        today.get(Calendar.DAY_OF_MONTH)

    ).show()
}