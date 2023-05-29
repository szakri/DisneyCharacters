package com.example.disneycharacters.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.disneycharacters.model.Character
import com.example.disneycharacters.ui.main.MainRepository
import retrofit2.HttpException
import java.io.IOException

class PagedSearchedCharacters (private val mainRepository: MainRepository, private val name: String): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int?
    {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPage = params.key ?: 1
            val characters = mainRepository.getCharacterByName(name, nextPage)
            LoadResult.Page(
                data = characters.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (characters.data.isEmpty()) null else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}