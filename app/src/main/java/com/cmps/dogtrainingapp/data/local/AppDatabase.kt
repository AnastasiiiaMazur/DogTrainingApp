package com.cmps.dogtrainingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cmps.dogtrainingapp.data.local.dao.HealthEventDao
import com.cmps.dogtrainingapp.data.local.dao.LessonProgressDao
import com.cmps.dogtrainingapp.data.local.dao.PetDao
import com.cmps.dogtrainingapp.data.local.dao.WalkDao
import com.cmps.dogtrainingapp.data.local.dao.WeightDao
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.local.entity.WalkEventEntity
import com.cmps.dogtrainingapp.data.local.entity.LessonProgressEntity
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity

@Database(
    entities = [PetEntity::class,
        HealthEventEntity::class,
        WalkEventEntity::class,
        LessonProgressEntity::class,
        WeightEntryEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun petDao(): PetDao
    abstract fun healthEventDao(): HealthEventDao
    abstract fun walkEventDao(): WalkDao
    abstract fun lessonProgressDao(): LessonProgressDao
    abstract fun weightEntryDao(): WeightDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "dog_training_db"
            )
                .fallbackToDestructiveMigration(true)
                .build()
    }
}
