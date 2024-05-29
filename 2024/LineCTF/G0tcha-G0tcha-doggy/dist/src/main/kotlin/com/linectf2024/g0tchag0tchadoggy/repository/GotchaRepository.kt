package com.linectf2024.g0tchag0tchadoggy.repository

import com.linectf2024.g0tchag0tchadoggy.model.GotchaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface GotchaRepository : JpaRepository<GotchaEntity, Long>{
    @Query("SELECT e FROM GotchaEntity e WHERE e.uuid = :uuid")
    fun findByUuid(uuid: UUID): GotchaEntity?
}

