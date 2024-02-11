package com.jn.airesponsematcher

import com.jn.airesponsematcher.extensions.addOperationWithArgs
import com.jn.airesponsematcher.extensions.processLines
import com.jn.airesponsematcher.extensions.replaceOperationWithNewValue
import com.jn.airesponsematcher.operation.OperationArg
import com.jn.airesponsematcher.operation.Operation
import com.jn.airesponsematcher.processor.Output


object FileOperations {

    data object RenameFile : Operation {
        override fun resolve(output: String, args: Map<String, String>?): String {
           return  renameFileExample(output, args)
        }

        override val name: String
            get() = "renameFile"
    }

    data object CreateFile : Operation {
        override fun resolve(output: String, args: Map<String, String>?): String {
            val fileName = args?.get(MyArgs.FILE_NAME.arg) ?: "Arquivo nao encontrado"
            return output.replaceOperationWithNewValue(CreateFile, "Arquivo $fileName criado com sucesso")
        }

        override val name: String
            get() = "createFile"
    }
}


//Cria uma enum para colocar seus argmumentos, ela deve se extender a OperationArg
enum class MyArgs(val arg: String) : OperationArg {
    PATH("path"),
    FILE_NAME("fileName"),
    MSG_ON_SUCCESS("msgOnSuccess"),
    MSG_ON_ERROR("msgOnError");

    override val value: String
        get() = arg

}

fun main() {

    /*Example input user:
    *
    * Renomeie a pasata musica para music, essa pasta está na pasta onde está a pasta Download
    *
    * */
    val aiOutputExample = buildString{
        appendLine("Claro, a pasta \"musica\" será renomeada para \"music\"")
        appendLine("""Status da operaçao: renameFile("path": "/storage/emulated/0/Download/musica", "fileName": "music", "msgOnSuccess": "Pasta renomeada com sucesso")""")
        appendLine("""Status da operaçao: createFile("path": "/storage/emulated/0/Download/musica", "fileName": "MyFile0.txt", "msgOnSuccess": "Pasta criada com sucesso")""")
        appendLine("Nova operaçao adicionada, Status: ".addOperationWithArgs(FileOperations.CreateFile, mapOf(
            "path" to "path_here",
            "fileName" to "MyFile.txt"
        )))
    }

    val operations = listOf(FileOperations.RenameFile, FileOperations.CreateFile)
    val output = Output(
        output = aiOutputExample,
        operations = operations)

    //Chama a  funçao para processar a saida
    println(output.processLines())
}

fun renameFileExample(output: String, args: Map<String, String>?): String {
    val msgOnSuccess = args?.get(MyArgs.MSG_ON_SUCCESS.arg)
    val msgOnError = "Nao foi possivel renomear o arquivo"
    val filePath = args?.get(MyArgs.FILE_NAME.arg)
    val newFileName = args?.get(MyArgs.FILE_NAME.arg)

    // Implemente sua lógica para renomear o arquivo

    return output.replaceOperationWithNewValue(FileOperations.RenameFile, msgOnSuccess ?: msgOnError)
}