package org.plc4x.kmp.plc

import kotlinx.coroutines.flow.Flow
import org.plc4x.kmp.core.ValueModel
import org.plc4x.kmp.domain.model.*
import org.plc4x.kmp.domain.repository.DeviceRepository

class PlcDeviceRepository : DeviceRepository {

    private inner class FacilitiesValueModel : ValueModel<Facilities>(
        load()
    )

    private inner class CategoriesValueModel : ValueModel<Categories>(
        Categories()
    )

    private inner class ProtocolsValueModel : ValueModel<Protocols>(
        Protocols(
            listOf(
                Protocol("KNX", IdIdentity("KNX")),
            )
        )
    )

    private val _facilities = FacilitiesValueModel()
    override val facilities: Flow<Facilities>
        get() = _facilities.model

    private val _categories = CategoriesValueModel()
    override val categories: Flow<Categories>
        get() = _categories.model

    private val _protocols = ProtocolsValueModel()
    override val protocols: Flow<Protocols>
        get() = _protocols.model

    override fun updateFacilities(facilities: Facilities) {
        _facilities.setValue(facilities)
    }

    override fun updateCategories(categories: Categories) {
        _categories.setValue(categories)
    }

    override fun updateProtocols(protocols: Protocols) {
        _protocols.setValue(protocols)
    }

    fun load() = Facilities(
        listOf(
            Facility(
                name = "Smarthome",
                groups = Groups(listOf(Group("Living Room", IdIdentity("Kitchen")))),
                devices = listOf(
                    Devices(
                        IdIdentity("KNX"),
                        listOf(
                            Device(
                                name = "Light",
                                groupId = IdIdentity("Living Room"),
                                categoryId = IdIdentity("Light")
                            )
                        )
                    )
                )
            )
        )
    )


}