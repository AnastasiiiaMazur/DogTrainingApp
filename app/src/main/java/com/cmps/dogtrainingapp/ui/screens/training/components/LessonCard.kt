package com.cmps.dogtrainingapp.ui.screens.training.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.model.course.Lesson
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Orange

@Composable
fun LessonCard(
    lesson: Lesson,
    isCompleted: Boolean,
    isUnlocked: Boolean,
    onClick: () -> Unit,
    onLockedClick: () -> Unit
) {
    val context = LocalContext.current

    val imageRes = context.resources.getIdentifier(
        lesson.image,
        "drawable",
        context.packageName
    )

    val statusIcon = when {
        isCompleted -> R.drawable.complete_icon
        isUnlocked -> R.drawable.unlocked_icon
        else -> R.drawable.locked_icon
    }

    val actionText = if (isCompleted) "Review" else "Start"

    Column (
        modifier = Modifier
        .padding(top = 10.dp)
        .clip(RoundedCornerShape(12.dp))
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            if (isCompleted || isUnlocked) {
                onClick()
            } else {
                onLockedClick()
            }
        }
    ) {

        Box(
            modifier = Modifier
                .height(150.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "lesson image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Image(
                painter = painterResource(id = statusIcon),
                contentDescription = "lesson completion image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 7.dp, end = 7.dp)
                    .size(25.dp)
                    .align(Alignment.TopEnd)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = actionText,
            color = Orange,
            fontFamily = MyFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = lesson.title,
            color = Black,
            fontFamily = MyFontFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

    }
}



@Preview(showBackground = true)
@Composable
fun LessonCardPreview() {

    val lesson = Lesson(
        id = "les-001",
        title = "Essential Commands",
        image = "mod1_l5",
        goal = "Establish a clear and reliable recall command that your dog responds to immediately.",
        duration = "15–20 minutes",
        steps = emptyList(),
        tips = emptyList(),
        completionCriteria = "Dog reliably comes immediately when called from short distances, successfully completing 8 out of 10 attempts.",
    )

    LessonCard(
        lesson = lesson,
        onClick = {},
        isCompleted = true,
        isUnlocked = true,
        onLockedClick = {}
    )
}