# Risk of Rain Calculator

This project is a Kotlin based toolkit for calculating values from the game **Risk of Rain 2**. It contains utilities for chest prices, enemy stats, item data and other formulas that may be useful for modding or analysis.

## Features

- Data models for chests and enemies with basic calculation helpers
- Example Swing UI for displaying chest costs
- JSON files with reference data for chests, enemies and items

## Project structure

```
src/main/kotlin/    Kotlin source code
src/main/resources/ JSON data files used by the application
build.gradle.kts    Gradle configuration
```

## Building

The project uses the Gradle wrapper. Compile everything with:

```bash
./gradlew build
```

This will download the required Kotlin dependencies and produce the build in `build/`.

## Running the example UI

A small Swing demo is included in `ChestDisplay.kt`. Run it using:

```bash
./gradlew run --quiet -PmainClass=org.example.view.ChestDisplayKt
```

The program will open a window showing chest prices based on the JSON data.

## JSON resources

All balance information lives in the `src/main/resources` folder:

- `Chests.json` – Cost and item chance information for different chest types
- `enemys.json` – Base stats for various enemies
- `difficulties.json` – Difficulty scaling factors
- `items.json` – Item descriptions and stats

Feel free to edit these files or add your own to adjust the calculator.

---

This project is a work in progress and not affiliated with the original game developers. Contributions are welcome!

