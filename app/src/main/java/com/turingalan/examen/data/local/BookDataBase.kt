package com.turingalan.examen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BookEntity::class],  // ← Qué tabla existe
    version = 1,
    exportSchema = false
)
abstract class BookDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}