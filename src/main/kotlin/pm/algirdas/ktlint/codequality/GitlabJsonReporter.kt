package pm.algirdas.ktlint.codequality

import CodeQualityReport
import com.google.gson.GsonBuilder
import com.pinterest.ktlint.core.LintError
import com.pinterest.ktlint.core.Reporter
import org.gradle.internal.impldep.com.jcraft.jsch.HASH
import org.gradle.internal.impldep.it.unimi.dsi.fastutil.Hash
import java.io.PrintStream
import java.util.concurrent.ConcurrentHashMap

class GitlabJsonReporter(val out: PrintStream) : Reporter {

    private val lintErrors = ArrayList<CodeQualityReport>()

    override fun onLintError(file: String, err: LintError, corrected: Boolean) {
        if (!corrected) {
            var filePath = file.escapeJsonValue()
            filePath = filePath.substring(filePath.indexOf("/app/"))
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