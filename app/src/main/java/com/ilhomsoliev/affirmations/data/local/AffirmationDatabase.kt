package com.ilhomsoliev.affirmations.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import com.ilhomsoliev.affirmations.data.local.model.Affirmation
import com.ilhomsoliev.affirmations.data.local.model.Category

@Database(
    entities = [Affirmation::class, Category::class],
    version = 4,
)
abstract class AffirmationDatabase : RoomDatabase() {

    abstract fun getAffirmationDao(): AffirmationDao

    companion object {

        @Volatile
        private var instance: AffirmationDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room
                .databaseBuilder(context.applicationContext, AffirmationDatabase::class.java, "AffirmationDatabase")
                .createFromAsset("affirmations_with_categories.db")
                //.createFromAsset("")
                .addMigrations(
                    AffirmationDatabase.MIGRATION_1_2,
                    AffirmationDatabase.MIGRATION_2_3,
                    AffirmationDatabase.MIGRATION_3_4
                )
                .build()

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE affirmation ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0;")
                database.execSQL("ALTER TABLE affirmation ADD COLUMN isManual INTEGER NOT NULL DEFAULT 0;")
            }
        }
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE affirmation ADD COLUMN isDarkPsychology INTEGER NOT NULL DEFAULT 0;")
            }
        }
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE affirmation ADD COLUMN categoryId INTEGER NOT NULL DEFAULT -1;")
                database.execSQL(
                    "CREATE TABLE `Category` (`id` INTEGER, `name` TEXT NOT NULL, " +
                            "PRIMARY KEY(`id`))"
                )
            }
        }
    }
}