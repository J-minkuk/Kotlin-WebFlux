package com.hardy.practice.todo

import java.time.LocalDateTime
import javax.persistence.*

/**
 * 설명 : N/A
 *
 * @author Minkuk Jo / http://minkuk-jo.com
 * @since 2021. 02. 28.
 */
@Entity
@Table(name = "todos")
class Todo(
    @Column(name = "content")
    var content: String?,

    @Column(name = "done")
    var done: Boolean = false,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_at")
    var modifiedAt: LocalDateTime = createdAt
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}