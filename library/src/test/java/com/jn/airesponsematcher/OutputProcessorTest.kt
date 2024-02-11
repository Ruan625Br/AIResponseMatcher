package com.jn.airesponsematcher

import com.jn.airesponsematcher.extensions.processLines
import com.jn.airesponsematcher.extensions.replaceOperationWithNewValue
import com.jn.airesponsematcher.extensions.searchAllContent
import com.jn.airesponsematcher.operation.Operation
import com.jn.airesponsematcher.processor.Output
import org.junit.Test
import org.junit.Assert.*

class OutputProcessorTest {

    private val basePath = "/storage/emulated/0"


    object FileOperations {

        data object RenameFile : Operation {
            override fun resolve(output: String, args: Map<String, String>?): String {
                val fileName = args?.get("fileName") ?: ""
                return  output.replaceOperationWithNewValue(RenameFile, fileName)
            }

            override val name: String
                get() = "renameFile"
        }

    }


    @Test
    fun renameFileTest() {
        val fileName = "folder1"
        val folder2 = "folder2"
        val folder3 = "folder3"
        val base = "Iniciando processo para renomear o arquivo:"
        val operations = listOf(FileOperations.RenameFile)

        val rename: (String, String) -> String = { path, newName ->
            "$base renameFile(\"path\": \"$path\", \"fileName\": \"$newName\")"
        }

        val rename1 = rename("$basePath/$fileName", folder2)
        val rename2 = rename("$basePath/$fileName", folder3)
        val outputAI = buildString {
            appendLine(rename1)
            appendLine(rename2)
        }
        val output = Output(outputAI,operations)
        val expected = buildString {
            appendLine("$base $folder2")    
            appendLine("$base $folder3")
        }
        val result = output.processLines()

        println(result)
        assertEquals(expected, result)
    }

}
