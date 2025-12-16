package com.turingalan.examen.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.turingalan.examen.data.model.Book

@Entity("book")
data class BookEntity(
    @PrimaryKey
    val id:String,
    val title:String = "",
    val authors:List<String> = listOf(),
    val publicationYear:Int = 9999,
)

fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = this.id,
        title = this.title,
        authors = this.authors,
        publicationYear = this.publicationYear
    )
}

fun List<Book>.toEntity():List<BookEntity> = this.map(Book::toEntity)

fun BookEntity.toModel(): Book {
    return Book(
        id = this.id,
        title = this.title,
        authors = this.authors,
        publicationYear = this.publicationYear
    )
}

fun List<BookEntity>.toModel(): List<Book> { return this.map(BookEntity::toModel) }