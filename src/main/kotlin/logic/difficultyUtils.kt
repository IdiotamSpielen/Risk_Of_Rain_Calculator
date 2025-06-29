package logic

import org.example.DifficultyConfig
import kotlin.math.pow

object difficultyUtils {
    fun calculateCoefficient(config: org.example.view.DifficultyConfig): Float {
        return (config.playerFactor + config.timeInMinutes * config.timeFactor) * config.stageFactor
    }

    // calculate current Enemy-Level based on coeff
    fun calculeEnemyLevel(config: DifficultyConfig, coeff: Float): Int {
        return (1 + (coeff - config.playerFactor) / 0.33f).toInt()
    }

    fun calculateCost(baseCost: Int, coeff: Float): Int {
        return (baseCost * coeff.pow(1.25f)).toInt()
    }

    fun calculateEnemyReward(baseReward: Int, coeff: Float): Int {
        return (baseReward * coeff).toInt()
    }
}