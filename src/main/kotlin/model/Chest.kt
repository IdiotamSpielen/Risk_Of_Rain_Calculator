package org.example.models

import logic.difficultyUtils
import kotlin.random.Random

data class Chest(
    val name: String,
    val baseCost: Int,
    val guaranteedRarity: String?,
    val itemChances: Map<String, Double>?,
    val category: Boolean = false,
    val requiresKey: Boolean = false
) {
    /**
     * Calculates the cost of the chest based on the given coefficient.
     *
     * @param coeff The multiplier coefficient to adjust the base cost.
     * @return The calculated cost as an integer value.
     */
    fun calculateCost(coeff: Float): Int {
        return difficultyUtils.calculateCost(baseCost, coeff)
    }

    fun getRandomItem(): String {
        if (guaranteedRarity != null) return guaranteedRarity

        val chances = itemChances ?: return "Empty"
        val roll = Random.nextDouble(100.0)
        var cumulative = 0.0

        for ((rarity, chance) in chances) {
            cumulative += chance
            if (roll <= cumulative) return rarity
        }
        return "Empty"
    }
}
