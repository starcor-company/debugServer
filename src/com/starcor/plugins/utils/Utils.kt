package com.starcor.plugins.utils

import java.text.SimpleDateFormat
import java.util.*
import javax.swing.tree.TreeNode

/**
 * author：MrLSM
 * date：2018/9/26
 * description：
 */
object Utils {
    private val dateFormat = SimpleDateFormat("yyyy_MM_dd:HH_mm_ss")

    fun getCurrentTime(): String {
        return dateFormat.format(Date())
    }

    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }

    fun parseNodeToXML(treeNodes: List<TreeNode>) : String {
        val xmlNodes = StringBuffer()
        if (treeNodes.isNotEmpty()) {
            xmlNodes.append("<data>")
            treeNodes.indices
                    .map { treeNodes[it] }
                    .forEach { xmlNodes.append(it.toString()) }
            xmlNodes.append("</data>")
        }
        return xmlNodes.toString()
    }
}