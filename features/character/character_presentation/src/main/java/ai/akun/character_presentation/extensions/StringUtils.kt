package ai.akun.character_presentation.extensions

fun String.toLandscape(): String {
    return replace("portrait_uncanny", "landscape_incredible")
}