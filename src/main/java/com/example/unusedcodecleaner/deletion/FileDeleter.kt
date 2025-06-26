package com.example.unusedcodecleaner.deletion

import com.example.unusedcodecleaner.model.Report
import java.io.File

object FileDeleter {

    /**
     * Deletes fully empty files or removes specific unused elements.
     */
    fun applyFixes(report: Report) {
        val file = report.file
        var content = file.readText()

        // Naive removal of unused declarations (simple but risky; should be improved with AST)
        report.unusedMembers.forEach { member ->
            content = content.replaceFirst(Regex(".*$member.*\\n"), "")
        }

        if (content.isBlank()) {
            println("Deleting empty file: ${file.path}")
            file.delete()
        } else {
            file.writeText(content)
            println("Cleaned ${file.name} (saved)")
        }
    }
}
