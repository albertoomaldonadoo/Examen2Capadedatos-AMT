package com.turingalan.examen.data.local

class BookNotFoundException(
    message: String = "Libro no encontrado"
) : RuntimeException(message)