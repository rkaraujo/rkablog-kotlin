package software.renato.kotlin.rkablog.util

val SPACES_REGEX = Regex("\\s+")

fun String.slugfy(): String {
    return this.toLowerCase().replace(SPACES_REGEX, "-")
}


