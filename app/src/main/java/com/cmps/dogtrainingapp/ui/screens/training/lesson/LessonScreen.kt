package com.cmps.dogtrainingapp.ui.screens.training.lesson

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.model.course.Lesson
import com.cmps.dogtrainingapp.ui.components.BasicButton
import com.cmps.dogtrainingapp.ui.screens.training.components.LessonItem
import com.cmps.dogtrainingapp.ui.screens.training.course.CourseUiState
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily

@Composable
fun LessonRoute(
    courseId: String?,
    lessonId: String?,
    viewModel: LessonViewModel,
    navController: NavHostController
) {

    LaunchedEffect(courseId) {
        if (courseId != null && lessonId != null) {
            viewModel.loadLesson(courseId, lessonId)
        }
    }

    val state = viewModel.uiState

    LessonScreen(
        state = state,
        navController = navController,
        onMarkCompleteClick = { viewModel.markLessonComplete() }
    )
}

@Composable
fun LessonScreen(
    state: LessonUiState,
    navController: NavHostController,
    onMarkCompleteClick: () -> Unit
) {
    val context = LocalContext.current
    val lesson = state.lesson ?: return

    val imageRes = context.resources.getIdentifier(
        lesson.image,
        "drawable",
        context.packageName
    )

    val customPadding = if (state.isCompleted) 10.dp else 65.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 30.dp,
                bottom = 30.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {

        Column(
            modifier = Modifier
                .padding(bottom = customPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Lesson Details",
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
                    .clickable { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(
                    text = lesson.title,
                    color = Black,
                    fontSize = 20.sp,
                    fontFamily = MyFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = lesson.duration,
                    color = Gray,
                    fontSize = 13.sp,
                    fontFamily = MyFontFamily,
                    fontWeight = FontWeight.Normal
                )
            }

            Text(
                text = lesson.goal,
                color = Black,
                fontSize = 18.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(15.dp))

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "course image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            lesson.steps.forEachIndexed { index, step ->

                LessonItem(
                    type = "Step",
                    item = step,
                    itemNum = index + 1
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Useful Tips",
                color = Black,
                fontSize = 18.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            lesson.tips.forEachIndexed { index, tip ->

                LessonItem(
                    type = "Tip",
                    item = tip,
                    itemNum = index + 1
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Completion Criteria: ${ lesson.completionCriteria }",
                color = Black,
                fontSize = 18.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }

        if (!state.isCompleted) {
            BasicButton(
                buttonText = "Mark Lesson Complete",
                onClick = {
                    onMarkCompleteClick()
                    Toast.makeText(
                        context,
                        "Progress saved!",
                        Toast.LENGTH_SHORT).show() },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessonScreenPreview() {

    val state = LessonUiState(
        lesson = Lesson(
            id = "les-001",
            title = "Lesson 1: Name Recognition",
            image = "mod1_l1",
            goal = "Teach your puppy to respond promptly and consistently to their name.",
            duration = "10–15 minutes",
            steps = listOf(
                "Gather small, tasty treats.",
                "Start in a quiet area.",
                "Say your puppy’s name clearly.",
                "Reward attention immediately."
            ),
            tips = listOf(
                "Use a friendly voice.",
                "Do not use the puppy’s name negatively."
            ),
            completionCriteria = "Your puppy turns toward you when called 8 out of 10 times."
        ),
        isCompleted = true
    )

    LessonScreen(
        state = state,
        navController = rememberNavController(),
        onMarkCompleteClick = {}
    )
}