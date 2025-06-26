package com.example.unusedcodecleaner.api

import com.example.unusedcodecleaner.analyzer.JavaAnalyzer
import com.example.unusedcodecleaner.analyzer.KotlinAnalyzer
import com.example.unusedcodecleaner.deletion.FileDeleter
import com.example.unusedcodecleaner.model.Report
import java.io.File

object UnusedCodeCleaner {

    /**
     * @param projectDir the root directory of your app/module
     * @param dryRun     true → only generate report; false → apply deletion
     * @return           list of reports per file
     */
    @JvmStatic
    fun clean(projectDir: File, dryRun: Boolean = true): List<Report> {
        val ktAnalyzer = KotlinAnalyzer()
        val javaAnalyzer = JavaAnalyzer()
        val reports = mutableListOf<Report>()

        projectDir.walkTopDown()
            .filter { it.isFile }
            .filter { it.extension == "kt" || it.extension == "java" }
            .filter { !it.invariantSeparatorsPath.contains("/build/") }   // skip generated files
            .forEach { file ->
                val content = file.readText()
                val report = when (file.extension) {
                    "kt" -> ktAnalyzer.analyze(content, file)
                    "java" -> javaAnalyzer.analyze(content, file)
                    else -> null
                }
                report?.let {
                    if (!dryRun) FileDeleter.applyFixes(it)
                    reports += it
                }
            }

        return reports
    }
}
