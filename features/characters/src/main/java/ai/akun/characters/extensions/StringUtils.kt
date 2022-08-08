package ai.akun.characters.extensions

fun String.toLandscape(): String {
    return replace("portrait_uncanny", "landscape_incredible")
}