package org.plc4x.kmp.ui.presentation.feature.main.model

import org.plc4x.kmp.domain.model.*
import org.plc4x.kmp.ui.platform.DeviceNode

fun mapFacilitDevices(
    devices: Facility,
    protocols: Protocols,
    groups: Groups
): List<DeviceNode> {
    return protocols.protocols.map { protocol ->
        // e.g. KNX, KNX Modbus
        val protocolDevices = devices.devices.filter { it.protocolId.id == protocol.protocolId.id }
        val items = protocolDevices.map { devices ->
            DeviceNode(
                name = protocol.name,
                children = mapProtocol(devices, groups),
                isProtocol = true,
                hasChildren = protocolDevices.isNotEmpty()
            )
        }
        DeviceNode(
            name = protocol.name,
            children = items,
            isProtocol = true,
            hasChildren = protocolDevices.isNotEmpty()
        )
    }
}

fun mapProtocol(devices: Devices, groups: Groups): List<DeviceNode> {
    return groups.groups.map { group ->
        // e.g. Living Room, Kitchen
        val groupDevices = devices.devices.filter { it.groupId == group.groupId }
        DeviceNode(
            name = group.name,
            children = mapGroup(groupDevices),
            isProtocol = false,
            hasChildren = true
        )
    }
}

fun mapGroup(devices: List<Device>): List<DeviceNode> {
    return devices.map { device ->
        // e.g. Light, Heating
        DeviceNode(
            name = device.name,
            children = emptyList(),
            isProtocol = false,
            hasChildren = false
        )
    }
}

fun mapFacilitiesToTree(
    facilities: Facilities,
    protocols: Protocols
): DeviceTreeNode {
    return DeviceTreeNode(
        DeviceNode(
            "IoT",
            false,
            facilities.facilities.map { facility ->
                DeviceNode(
                    facility.name,
                    false,
                    mapFacilitDevices(facility, protocols, facility.groups),
                    true
                )
            }, true
        )
    )
}