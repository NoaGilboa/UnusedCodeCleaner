# UnusedCodeCleaner

**UnusedCodeCleaner** is a lightweight Kotlin/Java static analysis library designed to detect and optionally remove unused functions and fields from your Android or Java/Kotlin projects.

---

## 📦 Features

- 🔍 Scans `.kt` and `.java` files for:
  - Unused methods/functions
  - Unused variables/fields
- 🧾 Generates a clean and readable **report**
- ✂️ Optionally **removes** unused members or deletes empty files
- 🚫 Ignores auto-generated and `build/` directories
- 🪶 No dependencies — works standalone

---

## 🛠 How It Works

The library performs static analysis by scanning each Kotlin/Java file and checking if methods/fields are used elsewhere in the same file. It considers an element *unused* if it only appears in its own declaration.

> ⚠️ The detection is approximate. For 100% safety, use `dryRun = true` and review the report before enabling deletion.

---

## 🚀 Getting Started

### 1. Add to Your Project

Clone or copy this library as a module or dependency in your project.

```kotlin
dependencies {
    implementation(project(":cleaner"))
}
````

### 2. Run the Built-in Main File

The library already includes a sample `main.kt` runner that scans your project and generates a report.

Make sure to **update the `projectDir` path** inside `main.kt` to point to the root folder of your app:

```kotlin
val projectDir = File("app") // Or use the full absolute path
```

Then, simply run the `main.kt` file from your IDE or terminal:

```bash
./gradlew :cleaner:run
```

> 📌 You must have the Kotlin `application` plugin configured in the `build.gradle.kts` of the module if running via Gradle CLI.

---

## 📄 Report Example

After running the tool, a `unused_report.txt` file will be generated in the root directory:

```
src/main/java/com/example/MyClass.java
 • Unused methods : 2
   - calculateResult
   - logDebug

 • Unused fields  : 1
   - unusedFlag
```

---

## 🧯 Want to Remove Unused Code?

If you want to apply the fixes (i.e., delete unused lines or entire empty files), just set `dryRun = false` in `main.kt`:

```kotlin
val reports = UnusedCodeCleaner.clean(projectDir, dryRun = false)
```

> ⚠️ Always back up your code or use version control before applying fixes.

---

## 📁 Directory Structure

```
cleaner/
├── analyzer/
│   ├── JavaAnalyzer.kt
│   └── KotlinAnalyzer.kt
├── api/
│   └── UnusedCodeCleaner.kt
├── deletion/
│   └── FileDeleter.kt
├── model/
│   └── Report.kt
└── main.kt
```

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.

---

## 🧠 License

MIT © 2025 — Created by [Noa Gilboa](https://github.com/NoaGilboa)

```

Let me know if you'd like a badge (e.g., license, build, version), or markdown tweaks like collapsible sections.
```
