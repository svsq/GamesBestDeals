package tk.svsq.gamesbestdeals.domain.repository

import tk.svsq.gamesbestdeals.domain.model.Deal
import tk.svsq.gamesbestdeals.domain.model.Store

interface GameRepository {

    suspend fun getStoresList(): Result<List<Store>>

    suspend fun getDealsList(storeId: String, upperPrice: String): Result<List<Deal>>
}