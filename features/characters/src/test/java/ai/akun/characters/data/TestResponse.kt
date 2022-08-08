package ai.akun.characters.data

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.core.network.responses.*
import java.util.*

object TestResponse {

    private val dataResponse = DataResponse(
        offset = 0,
        limit = 0,
        total = 0,
        count = 0,
        results = (1..6).map {
            val randomString = UUID.randomUUID().toString()
            CharacterResponse(
                id = (0..10).random().toLong(),
                name = randomString,
                description = randomString,
                thumbnail = CharacterThumbnailResponse(path = randomString, extension = randomString ),
                comics = ComicsResponse(available = 0, items = emptyList(),)
            )
        }
    )

    val baseResponse = BaseResponse(
        code = 0,
        status = "",
        data = dataResponse
    )

}