package org.example

import kotlin.math.pow

data class DifficultyConfig(
    val playerCount: Int,
    val difficultyValue: Int,
    val stagesCompleted: Int,
    val timeInMinutes: Float,
) {
    // Effekte des Schwierigkeitsgrad-Modifikators
    val playerFactor: Float
        get() = 1 + 0.3f * (playerCount - 1) // Spieler-Skalierung

    val timeFactor: Float
        get() = 0.0506f * difficultyValue * playerCount.toDouble().pow(0.2).toFloat()

    val stageFactor: Float
        get() = 1.15.pow(stagesCompleted.toDouble()).toFloat()
}
