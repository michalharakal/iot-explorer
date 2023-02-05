package org.plc4x.kmp.ui.platform

data class DeviceNode(
    val name: String,
    val isProtocol: Boolean,
    val children: List<DeviceNode>,
    val hasChildren: Boolean
)

val rootDeviceNode = DeviceNode("root", false, emptyList(), true)