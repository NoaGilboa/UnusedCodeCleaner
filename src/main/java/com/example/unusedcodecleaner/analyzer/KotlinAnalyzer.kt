package com.example.unusedcodecleaner.analyzer

import com.example.unusedcodecleaner.model.Report
import java.io.File

class KotlinAnalyzer {

    fun analyze(fileContent: String, file: File): Report {
        // === Functions ===
        val unusedFunctions = Regex("""fun\s+(\w+)\(""")
            .findAll(fileContent)
            .map { it.groupValues[1] }
            .filter { name ->
                Regex("""\b$name\s*\(""").findAll(fileContent).count() == 1
            }
            .toList()

        // === Variables ===
        val unusedVariables = Regex("""val\s+(\w+)[\s=:]""")
            .findAll(fileContent)
            .map { it.groupValues[1] }
            .filter { name ->
                Regex("""\b$name\b""").findAll(fileContent).count() == 1
            }
            .toList()

        return Report(file, unusedFunctions, unusedVariables)
    }
}
