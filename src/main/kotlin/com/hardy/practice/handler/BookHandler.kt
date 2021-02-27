package com.hardy.practice.handler

import com.hardy.practice.domain.Book
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 설명 : N/A
 *
 * @author Minkuk Jo / http://minkuk-jo.com
 * @since 2021. 02. 28.
 */
@Component
class BookHandler {

    fun allBooks(serverRequest: ServerRequest): Mono<ServerResponse> {
        val book1 = Book(1L, "해리포터")
        val book2 = Book(2L, "언어의온도")

        val flux = Flux.just(book1, book2)
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(flux, Book::class.java)
    }

}