package com.example.unusedcodecleaner.analyzer

import com.example.unusedcodecleaner.model.Report
import java.io.File

class JavaAnalyzer {

    fun analyze(fileContent: String, file: File): Report {
        // === Methods ===
        val unusedMethods = Regex("""(?:public|protected|private|static|\s)*\s*\w[\w<>]*\s+(\w+)\s*\(""")
            .findAll(fileContent)
            .map { it.groupValues[1] }
            .filter { name ->
                Regex("""\b$name\s*\(""").findAll(fileContent).count() == 1
            }
            .toList()

        // === Fields ===
        val unusedFields = Regex("""(?:public|protected|private|static|final|\s)*\s*\w[\w<>.\[\]]*\s+(\w+)\s*(=|;)""")
            .findAll(fileContent)
            .map { it.groupValues[1] }
            .filter { name ->
                Regex("""\b$name\b""").findAll(fileContent).count() == 1
            }
            .toList()

        return Report(file, unusedMethods, unusedFields)
    }
}
