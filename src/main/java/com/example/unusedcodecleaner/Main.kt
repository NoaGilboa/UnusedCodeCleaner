package com.example.unusedcodecleaner

import com.example.unusedcodecleaner.api.UnusedCodeCleaner
import java.io.File

fun main() {
    // Change "app" to the relative or absolute path of your target module
    val projectDir = File("app")
    println("Scanning in: ${projectDir.absolutePath} (exists=${projectDir.exists()})")

    val reports = UnusedCodeCleaner.clean(projectDir, dryRun = true)

    val outFile = File("unused_report.txt")
    outFile.printWriter().use { writer ->
        reports.forEach { report ->
            writer.println("${report.file.relativeTo(projectDir)}")
            writer.println(" • Unused methods   : ${report.unusedFunctionsOrMethods.size}")
            report.unusedFunctionsOrMethods.forEach { writer.println("   - $it") }

            writer.println(" • Unused fields    : ${report.unusedVariablesOrFields.size}")
            report.unusedVariablesOrFields.forEach { writer.println("   - $it") }

            writer.println()
        }
    }

    println("Report written to: ${outFile.absolutePath}")
}
