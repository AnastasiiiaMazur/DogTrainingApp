package com.cmps.dogtrainingapp.ui.screens.training.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.data.model.Course
import com.cmps.dogtrainingapp.ui.components.GradientDot
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.Green
import com.cmps.dogtrainingapp.ui.theme.LightGreen
import com.cmps.dogtrainingapp.ui.theme.LightRed
import com.cmps.dogtrainingapp.ui.theme.LightYellow
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Red
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.ui.theme.Yellow


@Composable
fun CourseCard(
    course: Course,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    val imageRes = context.resources.getIdentifier(
        course.imageName,
        "drawable",
        context.packageName
    )

    val colors = when(course.level) {
        "Beginner" -> listOf(Green, LightGreen)
        "Intermediate" -> listOf(Yellow, LightYellow)
        else -> listOf(Red, LightRed)
    }

    //painterResource(id = imageRes)

    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(200.dp)
    ) {

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "course image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = 0.25f))
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = course.title,
                color = White,
                fontFamily = MyFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.weight(1f))

            GradientDot(colors = colors)

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = course.level,
                color = White,
                fontFamily = MyFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {

    val course = Course(
        id = "1",
        title = "Essential Commands",
        description = "Teach fundamental commands every puppy needs.",
        level = "Beginner",
        imageName = "mod1_l1",
        lessons = emptyList()
    )

    CourseCard(
        course = course,
        onClick = {}
    )
}