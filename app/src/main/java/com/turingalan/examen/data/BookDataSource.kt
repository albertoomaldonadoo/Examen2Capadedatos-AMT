package com.turingalan.examen.data

import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.flow.Flow


// TODO Completar con los métodos necesarios
interface BookDataSource {

    suspend fun addAll(bookList: List<Book>)
    // ↑ Todos deben poder guardar libros

    fun observe(): Flow<Result<List<Book>>>
    // ↑ Todos deben poder observar cambios

    suspend fun readAll(): Result<List<Book>>
    // ↑ Todos deben poder leer todos
    suspend fun readOneByName(title: String): Result<Book>
    // ↑ Todos deben poder leer uno específico
    suspend fun readOneById(id: String): Result<Book>
    // ↑ Todos deben poder leer uno específico

    suspend fun isError()
    // ↑ Todos deben poder reportar errores
}