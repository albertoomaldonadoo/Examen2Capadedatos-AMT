package com.turingalan.examen.data.local

import com.turingalan.examen.common.exception.BookNotFoundException
import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(
    private val bookDao: BookDao
): BookDataSource {
    override suspend fun addAll(bookList: List<Book>) {
        bookDao.insert(bookList.toEntity())
    }

    override fun observe(): Flow<Result<List<Book>>> {
        return bookDao.observeAll()
            .map { entities ->
                try {
                    Result.success(entities.toModel())
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }
    }

    override suspend fun readAll(): Result<List<Book>> {
        return try {
            val entities = bookDao.getAll()  // Lee de BD
            Result.success(entities.toModel())  // Convierte y devuelve éxito
        } catch (e: Exception) {
            Result.failure(e)  // Si hay error, lo encapsula
        }
    }

    override suspend fun readOneById(id: String): Result<Book> {
        return try {
            val entity = bookDao.readBookById(id) // Lee de BD
            if (entity != null) {
                Result.success(entity.toModel())
            } else {
                Result.failure(BookNotFoundException())  // No existe
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun readOneByName(title: String): Result<Book> {
        return try {
            val entity = bookDao.readBookByTitle(title) // Lee de BD
            if (entity != null) {
                Result.success(entity.toModel())
            } else {
                Result.failure(BookNotFoundException())  // No existe
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isError() {
        TODO("Not yet implemented")
    }

}