package com.cmps.dogtrainingapp.ui.screens.health

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.components.BasicButton
import com.cmps.dogtrainingapp.ui.navigation.Routes
import com.cmps.dogtrainingapp.ui.navigation.navigateHome
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Red

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthEventRoute(
    viewModel: HealthEventViewModel,
    navController: NavHostController
) {
    val state = viewModel.uiState

    HealthEventScreen(
        state = state,
        navController = navController
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthEventScreen(
    state: HealthEventUiState,
    navController: NavHostController
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
                .clickable{ navController.navigateHome() }
        )

        Text(
            text = "My events",
            color = Black,
            fontSize = 20.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp)
        )

        // Event cards

        Text(
            text = "Weekly Summary",
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
            text = "Completed this week: ${state.completedEvents}",
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
            onClick = { navController.navigate(Routes.event(state.editingEventId))}
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
        navController = rememberNavController()
    )
}