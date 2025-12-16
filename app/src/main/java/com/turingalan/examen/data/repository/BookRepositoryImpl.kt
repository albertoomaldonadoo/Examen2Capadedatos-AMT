package com.turingalan.examen.data.repository

import com.turingalan.examen.data.local.BookDao
import com.turingalan.examen.data.local.BookEntity
import com.turingalan.examen.data.local.BookLocalDataSource
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.remote.BookApi
import com.turingalan.examen.data.remote.model.bookDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val pokemonDao: BookDao,
    private val api: BookApi,
    private val localDataSource: BookLocalDataSource,    // BD
) : BookRepository {
    override suspend fun readOne(id: String): Result<Book> {
        return localDataSource.readOneById(id)
    }

    override suspend fun observeByQuery(search: String): Flow<Result<List<Book>>> {
        return localDataSource.observe()
    }

    // Función de ayuda para convertir (Mapper)
    private fun bookDto.toEntity(): BookEntity {
        return BookEntity(
            id = this.id,
            title = this.title,
            authors = this.authors,
            publicationYear = this.publicationYear
        )
    }
}