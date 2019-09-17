package pm.algirdas.ktlint.codequality

import com.pinterest.ktlint.core.Reporter
import com.pinterest.ktlint.core.ReporterProvider
import java.io.PrintStream

class GitlabJsonReporterProvider : ReporterProvider {
    override val id: String = "gitlab-quality"
    override fun get(out: PrintStream, opt: Map<String, String>): Reporter = GitlabJsonReporter(out)
}
