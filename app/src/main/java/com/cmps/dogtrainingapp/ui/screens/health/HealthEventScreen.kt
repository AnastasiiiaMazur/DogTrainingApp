package com.cmps.dogtrainingapp.ui.screens.health

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.ui.components.BasicButton
import com.cmps.dogtrainingapp.ui.navigation.Routes
import com.cmps.dogtrainingapp.ui.navigation.navigateAllEvents
import com.cmps.dogtrainingapp.ui.navigation.navigateHome
import com.cmps.dogtrainingapp.ui.screens.health.components.HealthEventCard
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Red

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthEventRoute(
    viewModel: HealthEventViewModel,
    navController: NavHostController
) {
    val state = viewModel.uiState

    val context = LocalContext.current

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()

            viewModel.clearErrorMessage()
        }
    }

    HealthEventScreen(
        state = state,
        navController = navController,
        onCompleteClicked = { viewModel.onCompleteClicked(it) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthEventScreen(
    state: HealthEventUiState,
    navController: NavHostController,
    onCompleteClicked: (HealthEventEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 30.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Health Hub",
            color = Black,
            fontSize = 26.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .padding(top = 15.dp)

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                contentDescription = "Back button",
                tint = DarkGray,
                modifier = Modifier
                    .size(25.dp)
                    .clickable{ navController.navigateHome() }
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "View All",
                color = Red,
                fontSize = 15.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable{ navController.navigateAllEvents() }
            )
        }

        Text(
            text = "My events",
            color = Black,
            fontSize = 20.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp)
        )

        if (state.weeklyEvents.isEmpty()) {

            Text(
                text = "You don't have any events this week.",
                color = Gray,
                fontSize = 15.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 15.dp)
            )

        } else {

            state.weeklyEvents.forEach { event ->

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

        Text(
            text = "Summary",
            color = Black,
            fontSize = 20.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp)
        )

        Text(
            text = "Upcoming events: ${state.upcomingEvents}",
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = Black,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = "Completed events: ${state.completedEvents}",
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = Black,
            modifier = Modifier.padding(top = 5.dp)
        )

        Text(
            text = "Overdue: ${state.overdueEvents}",
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = Black,
            modifier = Modifier.padding(top = 5.dp)
        )

        BasicButton(
            buttonText = "Add Health Event",
            paddingTop = 15,
            onClick = { navController.navigate(Routes.addEditEvent()) }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewScreen() {
    HealthEventScreen(
        state = HealthEventUiState(

        ),
        navController = rememberNavController(),
        onCompleteClicked ={}
    )
}