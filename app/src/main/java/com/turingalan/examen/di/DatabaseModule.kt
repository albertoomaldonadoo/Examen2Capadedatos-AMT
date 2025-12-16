package com.turingalan.examen.di

import android.content.Context
import androidx.room.Room
import com.turingalan.examen.data.local.BookDao
import com.turingalan.examen.data.local.BookDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // ← Crea una sola instancia para toda la app
class DatabaseModule {

    @Provides
    @Singleton
    fun provideBookDatabase(@ApplicationContext context: Context): BookDataBase {
        return Room.databaseBuilder(
            context,
            BookDataBase::class.java,
            "book_database"  // ← Nombre del archivo en el equipo
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookDao(database: BookDataBase): BookDao {
        return database.bookDao()
    }
}