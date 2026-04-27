package com.cmps.dogtrainingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.cmps.dogtrainingapp.data.local.entity.LessonProgressEntity

@Dao
interface LessonProgressDao {

    @Query("""SELECT * FROM lesson_progress WHERE petId = :petId AND courseId = :courseId""")
    fun getLessonsForCourse(petId: Long, courseId: String): Flow<List<LessonProgressEntity>>

    @Query("""SELECT * FROM lesson_progress WHERE petId = :petId AND courseId = :courseId AND lessonId = :lessonId LIMIT 1""")
    suspend fun getLessonProgress(petId: Long, courseId: String, lessonId: String): LessonProgressEntity?

    @Query("""SELECT * FROM lesson_progress WHERE petId = :petId""")
    fun getAllForPet(petId: Long): Flow<List<LessonProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(progress: LessonProgressEntity)
}