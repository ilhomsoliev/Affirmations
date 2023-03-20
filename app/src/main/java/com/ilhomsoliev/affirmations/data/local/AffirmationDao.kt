package com.ilhomsoliev.affirmations.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilhomsoliev.affirmations.data.local.model.Affirmation
import com.ilhomsoliev.affirmations.data.local.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface AffirmationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAffirmation(affirmation: Affirmation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM affirmation ")
    fun getAllAffirmation(): Flow<List<Affirmation>>

    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * FROM affirmation WHERE isManual is 1")
    fun getManualAffirmation(): Flow<List<Affirmation>>

    @Query("DELETE FROM affirmation WHERE content NOT IN (SELECT MIN(content) FROM affirmation GROUP BY content)")
    fun deleteAllRepeatingElements()

    @Query("SELECT * FROM affirmation ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomAffirmation(): Affirmation

    @Query("DELETE FROM Affirmation WHERE id = :id")
    suspend fun deleteAffirmation(id: Int)

    @Query("DELETE FROM category WHERE id = :id")
    suspend fun deleteCategory(id: Int)

    @Query("SELECT * FROM affirmation WHERE id = :id")
    suspend fun getAffirmationById(id: Int): Affirmation?

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Int): Category?

    @Query("SELECT * FROM affirmation WHERE isFavorite is 1")
    fun getFavoriteAffirmations(): Flow<List<Affirmation>>

    @Query("SELECT * FROM affirmation WHERE categoryId = :id")
    fun getAffirmationsByCategoryId(id: Int): Flow<List<Affirmation>>
}