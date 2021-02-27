package com.hardy.practice.book

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 설명 : N/A
 *
 * @author Minkuk Jo / http://minkuk-jo.com
 * @since 2021. 02. 28.
 */
@RestController
class BookController {

    @GetMapping("/foo")
    fun foo(): String {
        return "foo"
    }

    @GetMapping("/mono")
    fun mono(): Mono<String> {
        return Mono.just("mono")
    }

    @GetMapping("/flux")
    fun flux(): Flux<String> {
        return Flux.just("one", "two", "three")
    }

}