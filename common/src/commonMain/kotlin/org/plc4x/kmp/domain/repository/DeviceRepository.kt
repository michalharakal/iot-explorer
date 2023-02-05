package org.plc4x.kmp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.plc4x.kmp.domain.model.Categories
import org.plc4x.kmp.domain.model.Facilities
import org.plc4x.kmp.domain.model.Protocols


interface DeviceRepository {
    val facilities: Flow<Facilities>
    val categories: Flow<Categories>
    val protocols: Flow<Protocols>

    fun updateFacilities(facilities: Facilities)
    fun updateCategories(categories: Categories)
    fun updateProtocols(protocols: Protocols)
}