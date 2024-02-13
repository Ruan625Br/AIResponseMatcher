
import com.jn.airesponsematcher.extensions.process
import com.jn.airesponsematcher.extensions.processLines
import com.jn.airesponsematcher.processor.Output
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class FileOperationsTest {

    private val basePath = "/storage/emulated/0"
    private val textOperationStatus = "Operation status:"
    private val operations = listOf(FileOperations.RenameFile, FileOperations.Write)

    @Test
    fun renameFileTest() {
        val folder2 = "folder2"
        val folder3 = "folder3"
        val pathFolder2 = "$basePath/$folder2"
        val pathFolder3 = "$basePath/$folder3"

        val rename: (String, String) -> String = { path, newName ->
            "$textOperationStatus renameFile(\"path\": \"$path\", \"fileName\": \"$newName\")"
        }

        val rename1 = rename(pathFolder2, folder2)
        val rename2 = rename(pathFolder3, folder3)
        val outputAI = buildString {
            appendLine(rename1)
            appendLine(rename2)
        }
        val output = Output(outputAI, operations)
        val expected = buildString {
            appendLine("$textOperationStatus Starting process to rename the file to: $folder2")
            appendLine("Path: $pathFolder2")
            appendLine("$textOperationStatus Starting process to rename the file to: $folder3")
            appendLine("Path: $pathFolder3")
        }
        val result = output.processLines()

        println(result)
        assertEquals(expected, result)
    }

    @Test
    fun writeTextTest(){
        val filePath = "$basePath/Download/MyFile.txt"
        val write: (String, String) -> String = { path, content ->
            "WriteFile: write_START\"path\": \"$path\", \"content\": \"$content\"END"
        }

        val content1 = "package com.my.package\nfun main(){\n    //code here\n}"
        val content2 =  "package com.my.package\nfun main(){\n   println(\"Hello world\")\n}"
        val write1 = write(filePath, content1)
        val write2 = write(filePath, content2)
        val outputAI = buildString {
            appendLine(write1)
            appendLine(write2)
            appendLine("Fim do game boi heheheheh\nAinda aqui?")

        }
        val output = Output(outputAI, operations)
        val expected = buildString {
            appendLine("WriteFile: \nPath: $filePath")
            appendLine("Content: $content1")
            appendLine()
            appendLine()
            appendLine("WriteFile: \nPath: $filePath")
            appendLine("Content: $content2")
            appendLine()
            appendLine()
            appendLine("Fim do game boi heheheheh\nAinda aqui?")
        }

        val result = output.process()
        println(result)
        assertEquals(expected, result)

    }
}
