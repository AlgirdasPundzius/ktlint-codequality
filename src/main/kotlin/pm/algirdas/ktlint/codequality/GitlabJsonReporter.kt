package pm.algirdas.ktlint.codequality

import CodeQualityReport
import com.google.gson.GsonBuilder
import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.core.Reporter
import java.io.PrintStream

class GitlabJsonReporter(val out: PrintStream) : Reporter {

    private val lintErrors = ArrayList<CodeQualityReport>()

    override fun onLintError(file: String, err: LintError, corrected: Boolean) {
        if (!corrected) {
            var filePath = file.escapeJsonValue()
            val substringValue = filePath.indexOf("/app/")
            filePath = filePath.substring(if (substringValue == -1) 0 else substringValue)
            lintErrors.add(CodeQualityReport(err.detail, err.hashCode().toString(), CodeQualityReport.Location(CodeQualityReport.Location.Lines(err.line, err.line), filePath)))
        }
    }

    override fun afterAll() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        out.print(gson.toJson(lintErrors))
    }

    private fun String.escapeJsonValue() =
            this.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\b", "\\b")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t")
}