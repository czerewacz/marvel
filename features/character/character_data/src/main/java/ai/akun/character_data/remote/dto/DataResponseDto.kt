package ai.akun.character_data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Marvel API data network response format.
 *
 * @param offset The requested offset (number of skipped results) of the call.
 * @param limit The requested result limit.
 * @param total The total number of resources available given the current filter set.
 * @param count The total number of results returned by this call.
 * @param results The list of [T] returned by the call.
 */
@Serializable
data class DataResponseDto<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)
