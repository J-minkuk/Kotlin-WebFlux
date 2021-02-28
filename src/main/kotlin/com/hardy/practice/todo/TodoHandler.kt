package com.hardy.practice.todo

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.LocalDateTime

/**
 * 설명 : N/A
 *
 * @author Minkuk Jo / http://minkuk-jo.com
 * @since 2021. 02. 28.
 */
@Suppress("ReactiveStreamsUnusedPublisher")
@Component
class TodoHandler(private val todoRepository: TodoRepository) {

    fun getAll(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Flux.just(todoRepository.findAll()))
            .switchIfEmpty { notFound().build() }
    }

    fun getById(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(todoRepository.findById(serverRequest.pathVariable("id").toLong())))
            .switchIfEmpty { notFound().build() }
    }

    fun save(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                serverRequest.bodyToMono(Todo::class.java)
                    .switchIfEmpty { Mono.empty() }
                    .let {
                        it.flatMap { todo ->
                            Mono.fromCallable { todoRepository.save(todo) }.then(Mono.just(todo))
                        }
                    }
            ).switchIfEmpty { notFound().build() }
    }

    fun done(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .body(
                Mono.justOrEmpty(todoRepository.findById(serverRequest.pathVariable("id").toLong()))
                    .switchIfEmpty { Mono.empty() }
                    .let {
                        it.flatMap { todo ->
                            Mono.fromCallable {
                                todo.done = true
                                todo.modifiedAt = LocalDateTime.now()
                                todoRepository.save(todo)
                            }.then(Mono.just(todo))
                        }
                    }
            ).switchIfEmpty { notFound().build() }
    }

    fun remove(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                Mono.justOrEmpty(todoRepository.findById(serverRequest.pathVariable("id").toLong()))
                    .switchIfEmpty { Mono.empty() }
                    .let {
                        it.flatMap { todo ->
                            Mono.fromCallable { todoRepository.delete(todo) }.then(Mono.just(todo))
                        }
                    }
            ).switchIfEmpty { notFound().build() }
    }

}