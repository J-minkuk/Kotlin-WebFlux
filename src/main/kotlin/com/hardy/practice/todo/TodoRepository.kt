package com.hardy.practice.todo

import org.springframework.data.jpa.repository.JpaRepository

/**
 * 설명 : N/A
 *
 * @author Minkuk Jo / http://minkuk-jo.com
 * @since 2021. 02. 28.
 */
interface TodoRepository : JpaRepository<Todo, Long> {
}