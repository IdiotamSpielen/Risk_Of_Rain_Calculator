package org.example.view

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import logic.difficultyUtils
import org.example.models.Chest
import java.awt.BorderLayout
import java.awt.GridLayout
import java.io.File
import javax.swing.*
import javax.swing.table.DefaultTableModel

@Serializable
data class DifficultyConfig(
    val playerFactor: Float,
    val timeInMinutes: Float,
    val timeFactor: Float,
    val stageFactor: Float
)

@Serializable
data class Chest(
    val name: String,
    val baseCost: Int,
    val guaranteedRarity: String? = null,
    val itemChances: Map<String, Double>? = null,
    val category: Boolean = false,
    val requiresKey: Boolean = false
)

fun main() {
    // GUI erstellen
    val frame = JFrame("Chest Cost Calculator")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.layout = BorderLayout()
    frame.setSize(600, 400)

    // Daten aus JSON-Datei laden
    val chests =
        loadChestData("C:\\Users\\Max\\IdeaProjects\\Risk_of_Rain_calculator\\src\\main\\resources\\Chests.json")

    // Panel für Eingaben
    val inputPanel = JPanel(GridLayout(3, 2))
    inputPanel.add(JLabel("Stage:"))
    val stageInput = JTextField("1")
    inputPanel.add(stageInput)

    inputPanel.add(JLabel("Time (minutes):"))
    val timeInput = JTextField("10.0")
    inputPanel.add(timeInput)

    inputPanel.add(JLabel("Players:"))
    val playerInput = JTextField("1")
    inputPanel.add(playerInput)
    frame.add(inputPanel, BorderLayout.NORTH)

    // Tabelle zur Anzeige der Kistendaten
    val columnNames = arrayOf("Name", "Base Cost", "Cost (Adjusted)")
    val tableModel = DefaultTableModel(columnNames, 0)
    val table = JTable(tableModel)
    frame.add(JScrollPane(table), BorderLayout.CENTER)

    // Button zur Berechnung
    val calculateButton = JButton("Calculate Costs")
    frame.add(calculateButton, BorderLayout.SOUTH)

    // Button-Action: Kosten berechnen und in der Tabelle anzeigen
    calculateButton.addActionListener {
        val stage = stageInput.text.toIntOrNull() ?: 1
        val time = timeInput.text.toFloatOrNull() ?: 10.0f
        val players = playerInput.text.toIntOrNull() ?: 1

        // Schwierigkeit basierend auf Eingabewerten berechnen
        val config = DifficultyConfig(
            playerFactor = 1 + (players - 1) * 0.5f,
            timeInMinutes = time,
            timeFactor = 0.05f,
            stageFactor = 1.0f + (stage - 1) * 0.1f
        )
        val coeff = difficultyUtils.calculateCoefficient(config)

        // Tabelle aktualisieren
        tableModel.rowCount = 0 // Alte Daten entfernen
        chests.forEach { chest ->
            val adjustedCost = chest.calculateCost(coeff)
            tableModel.addRow(arrayOf(chest.name, chest.baseCost, adjustedCost))
        }
    }

    // Fenster anzeigen
    frame.isVisible = true
}

// Funktion zum Laden der Daten aus der JSON-Datei (kotlinx-serialization Variante)
fun loadChestData(fileName: String): List<Chest> {
    val jsonString = File(fileName).readText() // Dateiinhalt lesen
    return Json.decodeFromString(jsonString) // JSON zu Liste decodieren
}