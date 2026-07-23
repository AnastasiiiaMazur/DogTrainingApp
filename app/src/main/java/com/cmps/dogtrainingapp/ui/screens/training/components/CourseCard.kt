package com.cmps.dogtrainingapp.ui.screens.training.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.data.model.course.Course
import com.cmps.dogtrainingapp.data.model.course.CourseLevel
import com.cmps.dogtrainingapp.data.model.course.Lesson
import com.cmps.dogtrainingapp.ui.components.GradientDot
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.Green
import com.cmps.dogtrainingapp.ui.theme.LightGray2
import com.cmps.dogtrainingapp.ui.theme.LightGreen
import com.cmps.dogtrainingapp.ui.theme.LightRed
import com.cmps.dogtrainingapp.ui.theme.LightYellow
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Orange
import com.cmps.dogtrainingapp.ui.theme.Red
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.ui.theme.Yellow


@Composable
fun CourseCard(
    course: Course,
    completedLessons: Int = 0,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    val imageRes = context.resources.getIdentifier(
        course.imageName,
        "drawable",
        context.packageName
    )

    val colors = when(course.level) {
        CourseLevel.BEGINNER -> listOf(Green, LightGreen)
        CourseLevel.INTERMEDIATE -> listOf(Yellow, LightYellow)
        else -> listOf(Red, LightRed)
    }

    val hasStarted = completedLessons > 0
    val totalLessons = course.lessons.size
    val progress = completedLessons.toFloat() / totalLessons

    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(200.dp)
            .clickable{ onClick() }
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

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (hasStarted) "Continue Course" else "Start Course",
                    color = White,
                    fontFamily = MyFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.weight(1f))

                GradientDot(colors = colors)

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = course.level.displayName,
                    color = White,
                    fontFamily = MyFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (hasStarted) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = course.title,
                        color = White,
                        fontFamily = MyFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "$completedLessons of $totalLessons lessons completed",
                        color = White,
                        fontFamily = MyFontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 6.dp)
                    )

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 6.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(50.dp)),
                        color = Orange,
                        trackColor = LightGray2,
                        strokeCap = Butt,
                        gapSize = 0.dp,
                        drawStopIndicator = {}
                    )
                }
            } else {
                Row {

                    Column(
                        modifier = Modifier
                            .weight(0.7f)
                    ) {
                        Text(
                            text = course.title,
                            color = White,
                            fontFamily = MyFontFamily,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = course.description,
                            color = White,
                            fontFamily = MyFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Text(
                        text = "${ course.lessons.size } lessons",
                        color = White,
                        fontFamily = MyFontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {

    val lessons = listOf(
        Lesson(
            id = "les-001",
            title = "Essential Commands",
            image = "mod1_l5",
            goal = "Establish a clear and reliable recall command that your dog responds to immediately.",
            duration = "15–20 minutes",
            steps = emptyList(),
            tips = emptyList(),
            completionCriteria = "Dog reliably comes immediately when called from short distances, successfully completing 8 out of 10 attempts.",
        ),
        Lesson(
            id = "les-002",
            title = "Essential Commands",
            image = "mod1_l5",
            goal = "Establish a clear and reliable recall command that your dog responds to immediately.",
            duration = "15–20 minutes",
            steps = emptyList(),
            tips = emptyList(),
            completionCriteria = "Dog reliably comes immediately when called from short distances, successfully completing 8 out of 10 attempts.",
        )
    )

    val course = Course(
        id = "1",
        title = "Essential Commands",
        description = "Teach fundamental commands every puppy needs.",
        level = CourseLevel.INTERMEDIATE,
        imageName = "mod1_l1",
        lessons = lessons
    )

    CourseCard(
        course = course,
        onClick = {},
        completedLessons = 1
    )
}