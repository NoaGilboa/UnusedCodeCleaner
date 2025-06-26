package com.example.unusedcodecleaner.model

import android.util.Log
import java.io.File

data class Report(
    val file: File,
    val unusedFunctionsOrMethods: List<String>,
    val unusedVariablesOrFields: List<String>
) {
    val unusedMembers: List<String>
        get() = unusedFunctionsOrMethods + unusedVariablesOrFields
}
