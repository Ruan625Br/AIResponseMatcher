---
sidebar_label: replaceOperationWithNewValue()
---

# `replaceOperationWithNewValue`
 A new string with the operation replaced by the new value.

:::info
If you want to replace multiple operations, consider using
<a href="./replaceAllOperationsWithNewValue">replaceAllOperationsWithNewValue</a>.
:::

## Usage

```kotlin
import com.jn.airesponsematcher.extensions.replaceOperationWithNewValue

...
val aiOutput = """Operation status: renameFile("path": "/storage/emulated/0/Download/MyFile0.txt", "fileName": "File1.txt")"""
val newValue = "Renamed successfully"

aiOutput.replaceOperationWithNewValue(FileOperations.RenameFile, newValue) //Operation status: Renamed successfully
```

```kotlin
fun String.replaceOperationWithNewValue(operation: OperationBase, newValue: String = ""): String 
```

### Parameters

| Parameter | Type | Default |
|-----------|------|---------|
|`operation`| `OperationBase`| - |
|`newValue`| `String`| `""` |
