package org.example.models

data class Enemy(
    val name: String,
    val baseHP: Float,
    val hpPerLevel: Float,
    val baseRegen: Float,
    val regenPerLevel: Float,
    val baseDamage: Float,
    val damagePerLevel: Float,
    val speed: Float,
    val armor: Int
) {
    /**
     * Calculates the total hit points (HP) of the enemy based on its level.
     *
     * @param level The current level of the enemy.
     * @return The calculated total HP of the enemy.
     */
    fun calculateHP(level: Int): Float {
        return baseHP + (hpPerLevel * (level - 1))
    }

    /**
     * Calculates the total damage dealt by the enemy based on its level.
     *
     * @param level The current level of the enemy.
     * @return The calculated total damage dealt by the enemy as a floating-point value.
     */
    fun calculateDamage(level: Int): Float {
        return baseDamage + (damagePerLevel * (level - 1))
    }
}
