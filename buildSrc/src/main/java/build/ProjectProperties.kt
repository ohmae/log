package build

object ProjectProperties {
    const val groupId: String = "net.mm2d.log"
    const val name: String = "Log"
    const val description: String = "This is a simple log print utils, like android.util.Log."
    const val developerId: String = "ryo"
    const val developerName: String = "ryosuke"

    private const val versionMajor: Int = 0
    private const val versionMinor: Int = 9
    private const val versionPatch: Int = 4
    const val versionName: String = "$versionMajor.$versionMinor.$versionPatch"
    const val versionCode: Int = versionMajor * 10000 + versionMinor * 100 + versionPatch

    object Url {
        const val site: String = "https://github.com/ohmae/log"
        const val github: String = "https://github.com/ohmae/log"
        const val scm: String = "scm:git@github.com:ohmae/log.git"
    }
}
