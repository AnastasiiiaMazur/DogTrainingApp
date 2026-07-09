package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.local.dao.LessonProgressDao
import com.cmps.dogtrainingapp.data.local.entity.LessonProgressEntity
import com.cmps.dogtrainingapp.data.model.Course
import com.cmps.dogtrainingapp.data.model.CourseLevel
import com.cmps.dogtrainingapp.data.model.Lesson
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import com.cmps.dogtrainingapp.data.source.json.TrainingJsonSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class TrainingRepository(
    private val trainingJsonSource: TrainingJsonSource,
    private val lessonProgressDao: LessonProgressDao,
    private val petPrefs: SelectedPetPreferences
) {

    fun getCourses(): List<Course> {
        return trainingJsonSource
            .getCourses()
            .sortedBy {
                when (it.level) {
                    CourseLevel.BEGINNER -> 0
                    CourseLevel.INTERMEDIATE -> 1
                    CourseLevel.ADVANCED -> 2
                }
            }
    }

    fun getCourse(courseId: String): Course? {
        return getCourses()
            .firstOrNull { it.id == courseId }
    }

    fun getLesson(
        courseId: String,
        lessonId: String
    ): Lesson? {
        return getCourse(courseId)
            ?.lessons
            ?.firstOrNull { it.id == lessonId }
    }

    suspend fun markLessonComplete(
        courseId: String,
        lessonId: String
    ) {
        lessonProgressDao.saveLessonProgress(
            LessonProgressEntity(
                petId = petPrefs.getSelectedPetId(),
                courseId = courseId,
                lessonId = lessonId,
                completed = true
            )
        )
    }

    suspend fun isLessonCompleted(
        courseId: String,
        lessonId: String
    ): Boolean {
        return lessonProgressDao.isLessonCompleted(
            petId = petPrefs.getSelectedPetId(),
            courseId = courseId,
            lessonId = lessonId
        ) ?: false
    }

    fun getCompletedLessonIdsForCourse(
        courseId: String
    ): Flow<Set<String>> {
        return petPrefs.selectedPetIdFlow
            .flatMapLatest { petId ->
                lessonProgressDao.getCompletedLessonIdsFlow(
                    petId = petId,
                    courseId = courseId
                )
            }
            .map { lessonIds ->
                lessonIds.toSet()
            }
    }

    fun getCompletedLessonCounts(): Flow<Map<String, Int>> {
        return petPrefs.selectedPetIdFlow
            .flatMapLatest { petId ->
                lessonProgressDao.getCompletedLessonCountsFlow(petId)
            }
            .map { progressList ->
                progressList.associate {
                    it.courseId to it.completedCount
                }
            }
    }
}