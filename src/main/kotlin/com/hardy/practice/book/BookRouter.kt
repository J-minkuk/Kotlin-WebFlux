package com.hardy.practice.book

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/**
 * 설명 : N/A
 *
 * @author Minkuk Jo / http://minkuk-jo.com
 * @since 2021. 02. 28.
 */
@Configuration
class BookRouter(private val handler: BookHandler) {

    @Bean
    fun route(): RouterFunction<ServerResponse> {
        return router { GET("/books", handler::allBooks) }
    }

}