---
sidebar_position: 3
---


## Operations

To create a operation, you need to extend the `Operation` interface.

### Example

```kotlin title="FileOperations.kt"
import com.jn.airesponsematcher.extensions.get
import com.jn.airesponsematcher.extensions.replaceOperationWithNewValue
import com.jn.airesponsematcher.operation.Operation

...

object FileOperations {

    data class RenameFile : Operation {
        override fun resolve(output: String, args: Map<String, String>?): String {
            val path = args?.get(MyArgs.PATH)
            val fileName = args?.get(MyArgs.FILE_NAME)
            val newValue = "Initiating the process to rename the file to: $fileName\nPath: $path"

            return output.replaceOperationWithNewValue(RenameFile, newValue)
        }

        override val name: String
            get() = "renameFile"
    }
}
```
### Explanation
When the library identifies your operation in the AI output, it will invoke the `resolve()` function. You should return the processed line within this function.

The `name` property represents the unique identifier for your operation.

The `replaceOperationWithNewValue` function replaces the identified operation in the output with the newly processed value.

## Args
:::info
Currently, in version 1.0.0-beta, the library does not directly require your arguments except for specific functions like `args?.get(MyArgs.FILE_NAME)`.
:::

To create an operation, you need to extend the OperationArg interface.

### Example

```kotlin
import com.jn.airesponsematcher.operation.OperationArg

...

enum class MyArgs(val arg: String) : OperationArg {
    PATH("path"),
    FILE_NAME("fileName");
    override val value: String
        get() = arg

}
```

### Explanation
When the library requires your argument, it will call the value property.

## Output

`Output` is the base class where everything happens. By default, this is the format in which operations should appear in the AI output:

```kotlin
yourOperationName("arg1": "value1", "arg2": "value2")
```

### Example

```kotlin
val operations = listOf(FileOperations.RenameFile, FileOperations.CreateFile)
val aiOutput = """Operation status: renameFile("path": "/storage/emulated/0/Download/MyFile0.txt", "fileName": "File1.txt")"""

val output = Output(aiOutput, operations)
val result = output.processLines()

println(result) 
//Operation status: Initiating the process to rename the file to: File1.txt
//                  Path: /storage/emulated/0/Download/MyFile0.txt
```

### Explanation

In this example, a list of operations is created, The AI output is then simulated with a specific format, and the Output class processes the lines, invoking the corresponding operations based on the recognized pattern.