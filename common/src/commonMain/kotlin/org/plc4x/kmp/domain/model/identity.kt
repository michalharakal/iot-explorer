package org.plc4x.kmp.domain.model

import org.plc4x.kmp.platform.randomUUID

interface Identity<T> {
    val id: T
}

typealias UUID = String

data class IdIdentity(override val id: UUID = randomUUID()) : Identity<UUID>