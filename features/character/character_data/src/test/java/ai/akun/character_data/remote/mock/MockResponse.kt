package ai.akun.character_data.remote.mock

import ai.akun.character_data.remote.dto.*
import java.util.*

object MockResponse {

    private val dataResponse = DataResponseDto(
        offset = 0,
        limit = 0,
        total = 0,
        count = 0,
        results = (1..6).map {
            val randomString = UUID.randomUUID().toString()
            CharacterResponseDto(
                id = (0..10).random().toLong(),
                name = randomString,
                description = randomString,
                thumbnail = CharacterThumbnailResponseDto(
                    path = randomString,
                    extension = randomString
                ),
                comics = ComicsResponseDto(available = 0, items = emptyList())
            )
        }
    )

    val baseResponse = BaseResponseDto(
        code = 0,
        status = "",
        data = dataResponse
    )
}