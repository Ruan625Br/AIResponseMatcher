## AIResponseMatcher

This library analyzes the output of AI and calls corresponding operations in a simple and straightforward manner. Designed for ease of use, AIResponseMatcher simplifies the interpretation of AI responses, enabling efficient integration with specific operations.

---
> [!WARNING]  
> This project is currently experimental and mostly just a proof-of-concept at this point. There are no tests and some things might be broken or very non-performant.
> The API may also change between releases without deprecation cycles.
---

## Consult the [documentation](https://ruan625br.github.io/AIResponseMatcher/)

## Usage

### Step 1. [Add the JitPack repository to the build file](https://jitpack.io/)

### Step 2. Add the dependency

#### Gradle: 

```java
implementation 'com.github.Ruan625Br:AIResponseMatcher:1.0.0-beta'
```
#### Gradle Kotlin DSL: 

```kotlin
implementation "com.github.Ruan625Br:AIResponseMatcher:1.0.0-beta"
```

#### Gradle version catlogs:

```kotlin
ai-reponse-matcher = "1.0.0-beta"
```

```kotlin
ai-reponse-matcher = { group = "com.github.Ruan625Br", name = "AIResponseMatcher", version.ref = "ai-reponse-matcher" }
```

#### Maven:

```xml
<dependency>
  <groupId>com.github.Ruan625Br</groupId>
  <artifactId>AIResponseMatcher</artifactId>
  <version>1.0.0-beta</version>
</dependency>
```

### Step 3: Configure Your Arguments

Define the arguments that your operations will use by creating an enum class. In this example, `MyArgs` represents the possible arguments for operations, such as the file path and file name.

```kotlin
enum class MyArgs(val arg: String) : OperationArg {
    PATH("path"),
    FILE_NAME("fileName");

    override val value: String
        get() = arg
}
```

### Step 4: Configure Your Operations

Create operations that will be performed based on the AI output. In this instance, the `FileOperations` object contains a `RenameFile` operation, which demonstrates how to handle renaming a file.

```kotlin
object FileOperations {

    data class RenameFile : Operation {
        override fun resolve(output: String, args: Map<String, String>?): String {
            val path = args?.get(MyArgs.PATH)
            val fileName = args?.get(MyArgs.FILE_NAME)
            val newValue = "Starting process to rename the file to: $fileName\nPath: $path"

            return output.replaceOperationWithNewValue(this, newValue)
        }

        override val name: String
            get() = "renameFile"
    }
}
```

### Step 5: Configure AI Output

Combine your AI output with the defined operations to create an `Output` instance. Here, the `operations` list includes the `RenameFile` and `CreateFile` operations.

```kotlin
val operations = listOf(FileOperations.RenameFile, FileOperations.CreateFile)
val output = Output(outputAI, operations)
```

### Step 6: Process Lines

Execute the operations on the AI output to process lines accordingly. In this step, the `processLines()` method is invoked on the `output` instance.

```kotlin
val result = output.processLines()
```
