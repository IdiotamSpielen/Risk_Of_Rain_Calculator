package org.example.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class ChestData(
    val name: String,
    val baseCost: Int,
    val guaranteedRarity: String?,
    val itemChances: Map<String, Double>?,
    val category: Boolean = false,
    val requiresKey: Boolean = false
)

object ChestLoader {
    fun loadChests(filePath: String): List<ChestData> {
        val jsonData = File(filePath).readText()
        return Json.decodeFromString(jsonData)
    }
}