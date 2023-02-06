package org.plc4x.kmp.ui.presentation.feature.main.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.plc4x.kmp.ui.platform.DeviceNode
import org.plc4x.kmp.ui.platform.rootDeviceNode

class ExpandableDeviceNode(
    val node: DeviceNode,
    val level: Int,
) {
    var children: List<ExpandableDeviceNode> by mutableStateOf(emptyList())
    val canExpand: Boolean get() = node.hasChildren

    fun toggleExpanded() {
        children = if (children.isEmpty()) {
            node.children
                .map { ExpandableDeviceNode(it, level + 1) }
                .sortedWith(compareBy({ it.node.isProtocol }, { it.node.name }))
                .sortedBy { !it.node.isProtocol }
        } else {
            emptyList()
        }
    }
}

class DeviceTreeNode(root: DeviceNode = rootDeviceNode) {
    private val expandableRoot = ExpandableDeviceNode(root, 0).apply {
        toggleExpanded()
    }

    val items: List<Item> get() = expandableRoot.toItems()

    inner class Item constructor(
        private val file: ExpandableDeviceNode
    ) {
        val name: String get() = file.node.name

        val level: Int get() = file.level

        val type: ItemType
            get() = if (file.node.isProtocol) {
                ItemType.Folder(isExpanded = file.children.isNotEmpty(), canExpand = file.canExpand)
            } else {
                ItemType.Device(category = "")
            }

        fun open() = when (type) {
            is ItemType.Folder -> file.toggleExpanded()
            is ItemType.Device -> {

            }

            else -> {}
        }
    }

    sealed class ItemType {
        class Folder(val isExpanded: Boolean, val canExpand: Boolean) : ItemType()
        class Device(val category: String) : ItemType()
    }

    private fun ExpandableDeviceNode.toItems(): List<Item> {
        fun ExpandableDeviceNode.addTo(list: MutableList<Item>) {
            list.add(Item(this))
            for (child in children) {
                child.addTo(list)
            }
        }

        val list = mutableListOf<Item>()
        addTo(list)
        return list
    }
}
