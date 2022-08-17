package com.lutas.authme.presentation.users.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lutas.authme.core.Config
import com.lutas.authme.core.remote.datasource.UserRemoteDataSource
import com.lutas.authme.domain.model.User

class UsersPagingSource(
    private val dataSource: UserRemoteDataSource
): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val since = params.key ?: 0
        return try {
            val users = dataSource.getUsers(since, params.loadSize).map { it.mapToModel() }
            var nextKey: Int? = since + params.loadSize
            if (nextKey!! >= Config.MAX_PAGER_ITEM_SIZE) {
                nextKey = null
            }
            LoadResult.Page(
                data = users,
                prevKey = if (since <= params.loadSize) null else since - params.loadSize,
                nextKey = nextKey
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }
}