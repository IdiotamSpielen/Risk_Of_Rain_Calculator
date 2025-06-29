package org.example.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.example.models.Enemy
import java.io.File

//Data-Class for the JSON-Parser
@Serializable
data class EnemyData(
    val name: String,
    val baseHP: Float,
    val hpPerLevel: Float,
    val baseRegen: Float,
    val regenPerLevel: Float,
    val baseDamage: Float,
    val damagePerLevel: Float,
    val speed: Float,
    val armor: Int
)

object EnemyLoader {
    fun loadEnemies(filePath: String): List<Enemy> {
        val jsonData = File(filePath).readText()
        val enemyDataList = Json.decodeFromString<List<EnemyData>>(jsonData)

        // Konvertiere EnemyData in Enemy-Instanzen
        return enemyDataList.map { data ->
            Enemy(
                name = data.name,
                baseHP = data.baseHP,
                hpPerLevel = data.hpPerLevel,
                baseRegen = data.baseRegen,
                regenPerLevel = TODO(),
                baseDamage = data.baseDamage,
                damagePerLevel = data.damagePerLevel,
                speed = data.speed,
                armor = data.armor
            )
        }
    }
}