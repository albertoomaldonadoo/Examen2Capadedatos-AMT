package com.turingalan.examen.data.remote

import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val bookApi: BookApi,
    @ApplicationScope private val scope: CoroutineScope
) : BookDataSource {
    override suspend fun addAll(bookList: List<Book>) {
        //Como se hace en el remoto no lo hacemos.
    }

    override fun observe(): Flow<Result<List<Book>>> {
        return flow {
            emit(Result.success(listOf<Book>()))
            val result = readAll()
            emit(result)
        }.shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000L),
            replay = 1
        )
    }

    override suspend fun readAll(): Result<List<Book>> {
        return try {
            val response = bookApi.getBooks()
            val bookList = response.results.map { bookDto ->
                Book(
                    id = bookDto.id,
                    title = bookDto.title,
                    authors = bookDto.authors,
                    publicationYear = bookDto.publicationYear
                )
            }
            Result.success(bookList)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun readOneById(id: String): Result<Book> {
        return try {
            val dto = bookApi.getBookById(id)

            val book = Book(
                id = dto.id,
                title = dto.title,
                authors = dto.authors,
                publicationYear = dto.publicationYear
            )

            Result.success(book)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun readOneByName(title: String): Result<Book> {
        return try {
            val dto = bookApi.getBookByTitle(title)

            val book = Book(
                id = dto.id,
                title = dto.title,
                authors = dto.authors,
                publicationYear = dto.publicationYear
            )

            Result.success(book)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isError() {
        TODO("Not yet implemented")
    }

}