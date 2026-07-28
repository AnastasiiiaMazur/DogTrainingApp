package com.cmps.dogtrainingapp.ui.screens.health.allevents

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.ui.components.BasicButton
import com.cmps.dogtrainingapp.ui.navigation.Routes
import com.cmps.dogtrainingapp.ui.navigation.navigateHome
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventUiState
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventViewModel
import com.cmps.dogtrainingapp.ui.screens.health.components.HealthEventCard
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Red


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ViewAllHealthEventsRoute(
    viewModel: HealthEventViewModel,
    navController: NavHostController
) {
    val state = viewModel.uiState

    ViewAllHealthEventsScreen(
        state = state,
        navController = navController,
        onCompleteClicked = { viewModel.onCompleteClicked(it) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ViewAllHealthEventsScreen(
    state: HealthEventUiState,
    navController: NavHostController,
    onCompleteClicked: (HealthEventEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 50.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Health Hub",
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
                .clickable{ navController.popBackStack() }
        )

        Text(
            text = "All ${state.totalEvents} events",
            color = Black,
            fontSize = 20.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp)
        )

        state.healthEvents.forEachIndexed { index, event ->

            HealthEventCard(
                event = event,
                onEditClicked = {
                    navController.navigate(
                        Routes.addEditEvent(event.id)
                    )
                },
                onCompleteClicked = {
                    onCompleteClicked(event)
                },
                navClick = {}
            )

            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}