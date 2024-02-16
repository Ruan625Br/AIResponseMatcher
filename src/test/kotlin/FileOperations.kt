
import com.jn.airesponsematcher.extensions.get
import com.jn.airesponsematcher.extensions.removeQuotes
import com.jn.airesponsematcher.extensions.replaceOperationWithNewValue
import com.jn.airesponsematcher.operation.Operation
import com.jn.airesponsematcher.operation.OperationArg
import com.jn.airesponsematcher.utils.Patterns

enum class MyArgs(val arg: String) : OperationArg {
    PATH("path"),
    FILE_NAME("fileName"),
    CONTENT("content");
    override val value: String
        get() = arg

}
object FileOperations {

    data object RenameFile : Operation {
        override fun resolve(output: String, args: Map<String, String>?): String {
            val path = args?.get(MyArgs.PATH)
            val fileName = args?.get(MyArgs.FILE_NAME)
            val newValue = "Starting process to rename the file to: $fileName\nPath: $path"

            return output.replaceOperationWithNewValue(RenameFile, newValue)
        }
        override val name: String
            get() = "renameFile"
    }

    data object CreateFile : Operation {
        override fun resolve(output: String, args: Map<String, String>?): String {
            val path = args?.get(MyArgs.PATH.arg)
            val fileName = args?.get(MyArgs.FILE_NAME.arg)
            val newValue = "Starting process to create the file: $fileName\nPath: $path"
            return output.replaceOperationWithNewValue(CreateFile, newValue)
        }

        override val name: String
            get() = "createFile"
    }

    data object Write : Operation {

        private const val mPattern = "\\s*START${Patterns.BASE_ARGUMENT}END"
        override fun resolve(output: String, args: Map<String, String>?): String {
            val path = args?.get(MyArgs.PATH)
            val content = args?.get(MyArgs.CONTENT)

            val newValue = buildString {
                appendLine("Path: $path")
                appendLine("Content: ${content?.removeQuotes()}")
            }

           return output.replaceOperationWithNewValue(this, newValue)
        }

        override val regex: Regex
            get() = "$name$mPattern".toRegex(RegexOption.DOT_MATCHES_ALL)
        override val name: String
            get() = "write"

    }
}