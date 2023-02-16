package ai.akun.character_data.repository

import ai.akun.character_data.mapper.toCharacterDomainEntity
import ai.akun.character_data.remote.client.MarvelApiClient
import ai.akun.core.network.error.NetworkFailure
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.single
import java.io.IOException
import java.nio.channels.UnresolvedAddressException

const val CHARACTERS_LOAD_SIZE = 10
private const val INITIAL_LOAD_SIZE = 0
private const val INITIAL_POSITION = 1

class CharactersPagingSource(private val apiClient: MarvelApiClient) :
    PagingSource<Int, ai.akun.character_domain.model.CharacterDomainEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ai.akun.character_domain.model.CharacterDomainEntity> {
        return try {

            val page = params.key ?: INITIAL_POSITION

            val offset =
                if (params.key != null) ((page - 1) * CHARACTERS_LOAD_SIZE) else INITIAL_LOAD_SIZE

            val response = apiClient.getCharacters(
                limit = params.loadSize,
                offset = offset
            ).single()

            //Handle initial load
            val prevKey = if (page > 1) page - (params.loadSize / CHARACTERS_LOAD_SIZE) else null

            //Handle end of list
            val nextKey = if (response.data.total < params.loadSize) {
                null
            } else {
                // initial load size = 3 * CHARGES_LOAD_SIZE
                page + (params.loadSize / CHARACTERS_LOAD_SIZE)
            }

            Log.d(
                "PAGINATION",
                "ParamsKey = ${params.key} Page= $page, Offset = $offset, PrevKey = $prevKey, NextKey = $nextKey, ParamsLoadSize = ${params.loadSize} "
            )

            LoadResult.Page(
                data = response.data.results.map { it.toCharacterDomainEntity() },
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: NetworkFailure) {
            return LoadResult.Error(exception)
        } catch (exception: UnresolvedAddressException) {
            return LoadResult.Error(NetworkFailure.NoInternetError("No internet connection"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ai.akun.character_domain.model.CharacterDomainEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}