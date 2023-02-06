package org.plc4x.kmp.domain.model

// protocols
data class Protocol(val name: String, val protocolId: IdIdentity)

data class Protocols(val protocols: List<Protocol> = emptyList())

// categories (Light, Heating, ...)

data class Category(val name: String, val categoryId: IdIdentity)

data class Categories(val categories: List<Category> = emptyList())

// Groups (Living Room, Kitchen, ...)
data class Group(val name: String, val groupId: IdIdentity)

data class Groups(val groups: List<Group> = emptyList())

// Devices
data class Device(val name: String, val groupId: IdIdentity, val categoryId: IdIdentity)

class Devices(val protocolId: IdIdentity, val devices: List<Device>)

// Facility

data class Facility(val name: String, val groups: Groups, val devices: List<Devices>)

data class Facilities(
    val facilities: List<Facility> = emptyList()
)

