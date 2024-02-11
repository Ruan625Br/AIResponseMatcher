
import com.jn.airesponsematcher.extensions.get
import com.jn.airesponsematcher.extensions.replaceOperationWithNewValue
import com.jn.airesponsematcher.operation.Operation
import com.jn.airesponsematcher.operation.OperationArg

enum class MyArgs(val arg: String) : OperationArg {
    PATH("path"),
    FILE_NAME("fileName");
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
}