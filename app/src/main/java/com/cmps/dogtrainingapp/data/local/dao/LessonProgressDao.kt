package com.cmps.dogtrainingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.cmps.dogtrainingapp.data.local.entity.LessonProgressEntity
import com.cmps.dogtrainingapp.data.model.CourseProgressCount

@Dao
interface LessonProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLessonProgress(progress: LessonProgressEntity)

    @Query("""
        SELECT completed
        FROM lesson_progress
        WHERE petId = :petId
          AND courseId = :courseId
          AND lessonId = :lessonId
    """)
    suspend fun isLessonCompleted(
        petId: Long,
        courseId: String,
        lessonId: String
    ): Boolean?

    @Query("""
        SELECT lessonId
        FROM lesson_progress
        WHERE petId = :petId
          AND courseId = :courseId
          AND completed = 1
    """)
    fun getCompletedLessonIdsFlow(
        petId: Long,
        courseId: String
    ): Flow<List<String>>

    @Query("""
        SELECT courseId, COUNT(*) as completedCount
        FROM lesson_progress
        WHERE petId = :petId
          AND completed = 1
        GROUP BY courseId
    """)
    fun getCompletedLessonCountsFlow(
        petId: Long
    ): Flow<List<CourseProgressCount>>
}