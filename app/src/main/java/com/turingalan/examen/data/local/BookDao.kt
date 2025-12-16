package com.turingalan.examen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface  BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookList: List<BookEntity>)

    @Query("SELECT * FROM book")
    fun observeAll(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book")
    suspend fun getAll(): List<BookEntity>

    @Query("SELECT * FROM book WHERE title = :title")
    suspend fun readBookByTitle(title: String): BookEntity?

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun readBookById(id: String): BookEntity?
}