package com.hardy.practice.todo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/**
 * 설명 : N/A
 *
 * @author Minkuk Jo / http://minkuk-jo.com
 * @since 2021. 02. 28.
 */
@Configuration
class TodoRouter(private val todoHandler: TodoHandler) {

    @Bean
    fun router(): RouterFunction<ServerResponse> {
        return nest(path("/todos"),
            router {
                listOf(
                    GET("", todoHandler::getAll),
                    GET("/{id}", todoHandler::getById),
                    POST("", todoHandler::save),
                    PUT("/{id}/done", todoHandler::done),
                    DELETE("/{id}", todoHandler::remove)
                )
            }
        )
    }

}
