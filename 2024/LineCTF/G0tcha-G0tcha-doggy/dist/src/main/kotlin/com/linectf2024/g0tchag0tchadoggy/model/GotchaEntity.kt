package com.linectf2024.g0tchag0tchadoggy.model

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import lombok.Setter
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
data class GotchaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "echo_id_seq")
    @SequenceGenerator(name = "echo_id_seq", allocationSize = 1)
    val id: Long = 0,

    @UuidGenerator
    var uuid: UUID = UUID.randomUUID(),

    @Column
    var userName: String = "anymous",

    @Column
    var userNumbers: List<Long> = emptyList(),

    @Column
    var gotchaNumbers: List<Long> = emptyList(),

    @Column
    var bonusNumber: Long = 0,

    @Column
    var result: Boolean = false,


) {

}